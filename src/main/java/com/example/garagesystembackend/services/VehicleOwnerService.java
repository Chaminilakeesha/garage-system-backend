package com.example.garagesystembackend.services;

import com.example.garagesystembackend.DTO.requests.LoginRequestDTO;
import com.example.garagesystembackend.DTO.requests.SignUpRequestDTO;
import com.example.garagesystembackend.DTO.requests.UpdateVehicleOwnerDTO;
import com.example.garagesystembackend.DTO.responses.JwtResponseDTO;
import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.repositories.VehicleOwnerRepository;
import com.example.garagesystembackend.services.interfaces.IVehicleOwnerService;
import com.example.garagesystembackend.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VehicleOwnerService implements IVehicleOwnerService {

    private VehicleOwnerRepository vehicleOwnerRepository;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public List<VehicleOwner> getAllVehicleOwners(){
        return vehicleOwnerRepository.findAll();
    }

    public VehicleOwner getVehicleOwner(int ownerId){
        return vehicleOwnerRepository.findByOwnerId(ownerId);
    }

    public void updateVehicleOwner(UpdateVehicleOwnerDTO updateVehicleOwnerDTO,int ownerId){
        VehicleOwner selectedVehicleOwner = vehicleOwnerRepository.findByOwnerId(ownerId);
        VehicleOwner vehicleOwner = new VehicleOwner(
                ownerId,
                updateVehicleOwnerDTO.getOwnerName(),
                updateVehicleOwnerDTO.getEmail(),
                updateVehicleOwnerDTO.getMobileNo(),
                selectedVehicleOwner.getPassword(),
                selectedVehicleOwner.getVehicles(),
                selectedVehicleOwner.getAppointments()
        );
        vehicleOwnerRepository.save(vehicleOwner);

    }

    public JwtResponseDTO registerVehicleOwner(SignUpRequestDTO signUpRequestDTO){

//        if (VehicleOwnerRepository.existsByEmail(signUpRequestDTO.getEmail())) {
//            throw new IllegalArgumentException("Email already exists");
//        }
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

    public JwtResponseDTO loginVehicleOwner(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        var vehicleOwner = vehicleOwnerRepository.findByEmail(loginRequestDTO.getEmail());
        var jwtToken = jwtUtils.generateToken(vehicleOwner.getEmail());
        return new JwtResponseDTO(vehicleOwner.getOwnerId(),"User login successfully",jwtToken);

    }




}
