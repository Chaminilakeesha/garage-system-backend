package com.example.garagesystembackend.DTO.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookAppointmentRequestDTO {

    private int vehicleId;

    private String date;

    private int slotId;

    private String description;
}
