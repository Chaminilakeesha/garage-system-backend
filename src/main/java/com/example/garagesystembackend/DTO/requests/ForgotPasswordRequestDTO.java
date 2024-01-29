package com.example.garagesystembackend.DTO.requests;

import javax.validation.constraints.Email;

public class ForgotPasswordRequestDTO {
    @Email(message = "Email should be valid")
    private String email;
}
