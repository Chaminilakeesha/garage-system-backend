package com.example.garagesystembackend.services.interfaces;

import com.example.garagesystembackend.DTO.requests.ForgotPasswordRequestDTO;
import com.example.garagesystembackend.DTO.requests.LoginRequestDTO;
import com.example.garagesystembackend.DTO.requests.SignUpRequestDTO;
import com.example.garagesystembackend.DTO.requests.UpdateVehicleOwnerDTO;
import com.example.garagesystembackend.DTO.responses.JwtResponseDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.models.VehicleOwner;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public interface IVehicleOwnerService {


    VehicleOwner getVehicleOwner(int ownerId);

    MessageResponseDTO updateVehicleOwner(UpdateVehicleOwnerDTO updateVehicleOwnerDTO, int ownerId);

    JwtResponseDTO registerVehicleOwner(SignUpRequestDTO signUpRequestDTO);

    JwtResponseDTO loginVehicleOwner(LoginRequestDTO loginRequestDTO);

    MessageResponseDTO logoutVehicleOwner(HttpServletRequest request);

    MessageResponseDTO forgotPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO);
}
