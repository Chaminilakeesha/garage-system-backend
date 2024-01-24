package com.example.garagesystembackend.DTO.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SignUpRequestDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Email must not be blank")
    private String mobileNo;

    @NotBlank(message = "Mobile Number must not be blank")
    private String password;

}
