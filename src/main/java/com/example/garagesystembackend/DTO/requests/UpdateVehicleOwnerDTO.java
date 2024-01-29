package com.example.garagesystembackend.DTO.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleOwnerDTO {

    private String ownerName;
    private String email;
    private String mobileNo;
}
