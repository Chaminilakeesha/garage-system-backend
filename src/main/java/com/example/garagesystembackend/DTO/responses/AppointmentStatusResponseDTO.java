package com.example.garagesystembackend.DTO.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentStatusResponseDTO {
    private int appointmentId;
    private String status;

}
