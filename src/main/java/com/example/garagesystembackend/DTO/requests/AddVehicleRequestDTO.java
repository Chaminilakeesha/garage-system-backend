package com.example.garagesystembackend.DTO.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddVehicleRequestDTO {

    private String vehicleNo;
    private String model;

}
