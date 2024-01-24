package com.example.garagesystembackend.controllers;

import com.example.garagesystembackend.DTO.requests.ForgotPasswordRequestDTO;
import com.example.garagesystembackend.DTO.requests.LoginRequestDTO;
import com.example.garagesystembackend.DTO.requests.ResetPasswordRequestDTO;
import com.example.garagesystembackend.DTO.requests.SignUpRequestDTO;
import com.example.garagesystembackend.DTO.responses.JwtResponseDTO;
import com.example.garagesystembackend.DTO.responses.LoginResponseDTO;
import com.example.garagesystembackend.services.VehicleOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    @Autowired
    public VehicleOwnerService vehicleOwnerService;
    @PostMapping("/register")
    public ResponseEntity<JwtResponseDTO> registerVehicleOwner(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return ResponseEntity.ok(vehicleOwnerService.registerVehicleOwner(signUpRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> loginVehicleOwner(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(vehicleOwnerService.loginVehicleOwner(loginRequestDTO));
    }

//    @PostMapping("/forgotPassword")
//    public ResponseEntity<LoginResponseDTO> forgotPassword(@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO){
//        return ResponseEntity.ok(vehicleOwnerService.forgotPassword(forgotPasswordRequestDTO));
//    }
//
//    @PostMapping("/resetPassword")
//    public ResponseEntity<LoginResponseDTO> resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO
//        return ResponseEntity.ok(vehicleOwnerService.resetPassword(resetPasswordRequestDTO
//    }


}
