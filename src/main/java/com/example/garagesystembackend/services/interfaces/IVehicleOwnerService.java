package com.example.garagesystembackend.services.interfaces;

import com.example.garagesystembackend.DTO.requests.LoginRequestDTO;
import com.example.garagesystembackend.DTO.requests.SignUpRequestDTO;
import com.example.garagesystembackend.DTO.requests.UpdateVehicleOwnerDTO;
import com.example.garagesystembackend.DTO.responses.JwtResponseDTO;
import com.example.garagesystembackend.DTO.responses.LoginResponseDTO;
import com.example.garagesystembackend.models.VehicleOwner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IVehicleOwnerService {

    List<VehicleOwner> getAllVehicleOwners();

    VehicleOwner getVehicleOwner(int ownerId);

    void updateVehicleOwner(UpdateVehicleOwnerDTO updateVehicleOwnerDTO, int ownerId);

    JwtResponseDTO registerVehicleOwner(SignUpRequestDTO signUpRequestDTO);

    JwtResponseDTO loginVehicleOwner(LoginRequestDTO loginRequestDTO);
}
