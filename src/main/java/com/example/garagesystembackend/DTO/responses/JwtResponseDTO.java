package com.example.garagesystembackend.DTO.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponseDTO {
    private int vehicleOwnerId;
    private String message;
    private String token;

}