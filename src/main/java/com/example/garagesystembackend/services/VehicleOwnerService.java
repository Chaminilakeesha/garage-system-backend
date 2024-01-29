package com.example.garagesystembackend.services;

import com.example.garagesystembackend.DTO.requests.ForgotPasswordRequestDTO;
import com.example.garagesystembackend.DTO.requests.LoginRequestDTO;
import com.example.garagesystembackend.DTO.requests.SignUpRequestDTO;
import com.example.garagesystembackend.DTO.requests.UpdateVehicleOwnerDTO;
import com.example.garagesystembackend.DTO.responses.ExtractTokenResponseDTO;
import com.example.garagesystembackend.DTO.responses.JwtResponseDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.filters.JwtAuthenticationFilter;
import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.repositories.VehicleOwnerRepository;
import com.example.garagesystembackend.services.interfaces.IVehicleOwnerService;
import com.example.garagesystembackend.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Service
public class VehicleOwnerService implements IVehicleOwnerService {
    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Autowired
    public JwtAuthenticationFilter jwtAuthenticationFilter;

    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

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
    public MessageResponseDTO forgotPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByEmail(forgotPasswordRequestDTO.getEmail());
        if(vehicleOwner == null){
            return new MessageResponseDTO("User not found");
        }else{

        }
        return new MessageResponseDTO("Password reset link sent to your email");
    }
}
