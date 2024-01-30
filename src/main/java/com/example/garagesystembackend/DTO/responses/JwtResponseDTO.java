package com.example.garagesystembackend.DTO.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponseDTO {
    private int vehicleOwnerId;
    private String type;
    private String message;
    private String token;


    public JwtResponseDTO(String type, String message) {
        this.type = type;
        this.message = message;
    }
}