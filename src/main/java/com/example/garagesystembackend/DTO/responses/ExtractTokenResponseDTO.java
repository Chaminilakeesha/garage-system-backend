package com.example.garagesystembackend.DTO.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtractTokenResponseDTO {

    private String token;

    private String email;
}
