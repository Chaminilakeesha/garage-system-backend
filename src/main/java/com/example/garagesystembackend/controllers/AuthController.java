package com.example.garagesystembackend.controllers;

import com.example.garagesystembackend.services.VehicleOwnerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private VehicleOwnerService vehicleOwnerService;

    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public void authenticateUser(@RequestBody String email, @RequestBody String password){

    }

}
