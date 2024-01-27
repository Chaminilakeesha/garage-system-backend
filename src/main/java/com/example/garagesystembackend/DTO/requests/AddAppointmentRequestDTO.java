package com.example.garagesystembackend.DTO.requests;

import com.example.garagesystembackend.models.Vehicle;
import com.example.garagesystembackend.models.VehicleOwner;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddAppointmentRequestDTO {

    private int appointmentId;

    private int vehicleId;


    private LocalDate date;

    private LocalTime time;

    private String description;
}
