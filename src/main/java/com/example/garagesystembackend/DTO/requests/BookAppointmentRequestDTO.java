package com.example.garagesystembackend.DTO.requests;

import com.example.garagesystembackend.models.TimeSlot;
import com.example.garagesystembackend.models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

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
