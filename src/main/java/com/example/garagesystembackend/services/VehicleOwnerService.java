package com.example.garagesystembackend.services;

import com.example.garagesystembackend.DTO.requests.*;
import com.example.garagesystembackend.DTO.responses.ExtractTokenResponseDTO;
import com.example.garagesystembackend.DTO.responses.JwtResponseDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.filters.JwtAuthenticationFilter;
import com.example.garagesystembackend.models.PasswordResetToken;
import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.repositories.PasswordResetTokenRepository;
import com.example.garagesystembackend.repositories.VehicleOwnerRepository;
import com.example.garagesystembackend.services.interfaces.IVehicleOwnerService;
import com.example.garagesystembackend.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class VehicleOwnerService implements IVehicleOwnerService {
    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public JwtAuthenticationFilter jwtAuthenticationFilter;

    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Autowired
    private EmailService emailService;

    @Override
    public VehicleOwner getVehicleOwner(int ownerId){
        return vehicleOwnerRepository.findByOwnerId(ownerId);
    }

    @Override
    public MessageResponseDTO updateVehicleOwner(UpdateVehicleOwnerDTO updateVehicleOwnerDTO, int ownerId){
        VehicleOwner selectedVehicleOwner = vehicleOwnerRepository.findByOwnerId(ownerId);
        VehicleOwner vehicleOwner = new VehicleOwner(
                ownerId,
                updateVehicleOwnerDTO.getOwnerName(),
                updateVehicleOwnerDTO.getEmail(),
                updateVehicleOwnerDTO.getMobileNo(),
                selectedVehicleOwner.getPassword()
        );
        vehicleOwnerRepository.save(vehicleOwner);
        return new MessageResponseDTO("success","Vehicle Owner updated successfully");

    }

    @Override
    public JwtResponseDTO registerVehicleOwner(SignUpRequestDTO signUpRequestDTO){

        VehicleOwner vehicleOwner = new VehicleOwner(
                signUpRequestDTO.getName(),
                signUpRequestDTO.getEmail(),
                signUpRequestDTO.getMobileNo(),
                this.passwordEncoder.encode(signUpRequestDTO.getPassword())
        );
        vehicleOwnerRepository.save(vehicleOwner);
        var jwtToken = jwtUtils.generateToken(vehicleOwner.getEmail());
        return new JwtResponseDTO(vehicleOwner.getOwnerId(),"success","User registered successfully",jwtToken);
    }

    @Override
    public JwtResponseDTO loginVehicleOwner(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        var vehicleOwner = vehicleOwnerRepository.findByEmail(loginRequestDTO.getEmail());
        var jwtToken = jwtUtils.generateToken(vehicleOwner.getEmail());
        return new JwtResponseDTO(vehicleOwner.getOwnerId(),"success","User login successfully",jwtToken);

    }

    @Override
    public MessageResponseDTO logoutVehicleOwner(HttpServletRequest request) {
        ExtractTokenResponseDTO extractTokenResponseDTO = jwtAuthenticationFilter.extractTokenFromRequest(request);
        System.out.println("token" + extractTokenResponseDTO.getToken());
        jwtUtils.addToBlacklist(extractTokenResponseDTO.getToken());
        return new MessageResponseDTO("success","Logged out successfully");

    }

    @Override
    public MessageResponseDTO forgotPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByEmail(forgotPasswordRequestDTO.getEmail());
        if(vehicleOwner == null){
            return new MessageResponseDTO("error","User not found");
        }else{
            if (passwordResetTokenRepository.existsByVehicleOwner(vehicleOwner)) {
                passwordResetTokenRepository.delete(passwordResetTokenRepository.findByVehicleOwner(vehicleOwner));
            }
            LocalDateTime expiration = LocalDateTime.now().plusHours(1);
            PasswordResetToken passwordResetToken = new PasswordResetToken(
                    UUID.randomUUID().toString(),
                    vehicleOwner,
                    expiration
            );
            passwordResetTokenRepository.save(passwordResetToken);

            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(vehicleOwner.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n"
                    + "http://localhost:3000/resetpassword?token=" + passwordResetToken.getToken());

            emailService.sendEmail(passwordResetEmail);
        }
        return new MessageResponseDTO("success","Password reset link sent to your email");
    }

    @Override
    public MessageResponseDTO resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(resetPasswordRequestDTO.getToken());
        VehicleOwner vehicleOwner = passwordResetToken.getVehicleOwner();
        boolean isTokenExpired = passwordResetToken.getExpiration().isBefore(LocalDateTime.now());
        if(vehicleOwner == null){
            return new MessageResponseDTO("error","Invalid token");
        }else if (isTokenExpired){
            return new MessageResponseDTO("error","Token expired");
        }else {
            vehicleOwner.setPassword(passwordEncoder.encode(resetPasswordRequestDTO.getPassword()));
            vehicleOwnerRepository.save(vehicleOwner);
            passwordResetTokenRepository.delete(passwordResetTokenRepository.findByToken(resetPasswordRequestDTO.getToken()));
            return new MessageResponseDTO("success","Password reset successfully");
        }
    }

    @Override
    public MessageResponseDTO changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, int ownerId) {
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByOwnerId(ownerId);
        String currentPassword = passwordEncoder.encode(changePasswordRequestDTO.getCurrentPassword());
        if (!passwordEncoder.matches(currentPassword, vehicleOwner.getPassword())) {
            return new MessageResponseDTO("error","Current password is incorrect");
        }else {
            vehicleOwner.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));
            vehicleOwnerRepository.save(vehicleOwner);
            return new MessageResponseDTO("success","Password changed successfully");
        }

    }
}
