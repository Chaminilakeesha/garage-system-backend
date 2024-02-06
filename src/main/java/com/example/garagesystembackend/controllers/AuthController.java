package com.example.garagesystembackend.controllers;

import com.example.garagesystembackend.DTO.requests.*;
import com.example.garagesystembackend.DTO.responses.JwtResponseDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.DTO.responses.RefreshTokenResponseDTO;
import com.example.garagesystembackend.services.RefreshTokenService;
import com.example.garagesystembackend.services.VehicleOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    @Autowired
    public VehicleOwnerService vehicleOwnerService;

    @Autowired
    public RefreshTokenService refreshTokenService;


    @PostMapping("/register")
    public ResponseEntity<JwtResponseDTO> registerVehicleOwner(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return ResponseEntity.ok(vehicleOwnerService.registerVehicleOwner(signUpRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> loginVehicleOwner(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(vehicleOwnerService.loginVehicleOwner(loginRequestDTO));
    }

    @GetMapping("/logout")
    public MessageResponseDTO logoutVehicleOwner(HttpServletRequest request) {
        return vehicleOwnerService.logoutVehicleOwner(request);
    }

    @PostMapping("/forgotPassword")
    public MessageResponseDTO forgotPassword(@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO){
        return vehicleOwnerService.forgotPassword(forgotPasswordRequestDTO);
   }

    @PostMapping("/resetPassword")
    public MessageResponseDTO resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO){
        return vehicleOwnerService.resetPassword(resetPasswordRequestDTO);
    }

    @PostMapping("/refreshToken")
    public RefreshTokenResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.getRefreshToken(refreshTokenRequestDTO);
    }



}
