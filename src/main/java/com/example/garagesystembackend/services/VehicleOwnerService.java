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
        return new MessageResponseDTO("Vehicle Owner updated successfully");

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
        return new JwtResponseDTO(vehicleOwner.getOwnerId(),"User registered successfully",jwtToken);
    }

    @Override
    public JwtResponseDTO loginVehicleOwner(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        var vehicleOwner = vehicleOwnerRepository.findByEmail(loginRequestDTO.getEmail());
        var jwtToken = jwtUtils.generateToken(vehicleOwner.getEmail());
        return new JwtResponseDTO(vehicleOwner.getOwnerId(),"User login successfully",jwtToken);

    }

    @Override
    public MessageResponseDTO logoutVehicleOwner(HttpServletRequest request) {
        ExtractTokenResponseDTO extractTokenResponseDTO = jwtAuthenticationFilter.extractTokenFromRequest(request);
        System.out.println("token" + extractTokenResponseDTO.getToken());
        jwtUtils.addToBlacklist(extractTokenResponseDTO.getToken());
        return new MessageResponseDTO("Logged out successfully");

    }

    @Override
    public MessageResponseDTO forgotPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO,HttpServletRequest request) {
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByEmail(forgotPasswordRequestDTO.getEmail());
        if(vehicleOwner == null){
            return new MessageResponseDTO("User not found");
        }else{
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
        return new MessageResponseDTO("Password reset link sent to your email");
    }

    @Override
    public MessageResponseDTO resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO) {
        VehicleOwner vehicleOwner = passwordResetTokenRepository.findVehicleOwnerByToken(resetPasswordRequestDTO.getToken());
        boolean isTokenExpired = passwordResetTokenRepository.findByToken(resetPasswordRequestDTO.getToken()).getExpiration().isBefore(LocalDateTime.now());
        if(vehicleOwner == null){
            return new MessageResponseDTO("Invalid token");
        }else if (!isTokenExpired){
            vehicleOwner.setPassword(passwordEncoder.encode(resetPasswordRequestDTO.getPassword()));
            vehicleOwnerRepository.save(vehicleOwner);
            passwordResetTokenRepository.delete(passwordResetTokenRepository.findByToken(resetPasswordRequestDTO.getToken()));
        }else{
            return new MessageResponseDTO("Token expired");
        }
        return new MessageResponseDTO("Password reset successfully");
    }
}
