package com.example.garagesystembackend.DTO.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshTokenResponseDTO {
    private String token;
    private String refreshToken;
}
