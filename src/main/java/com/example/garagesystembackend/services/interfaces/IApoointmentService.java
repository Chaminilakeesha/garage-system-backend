package com.example.garagesystembackend.services.interfaces;

import com.example.garagesystembackend.DTO.requests.AddAppointmentRequestDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.models.Appointment;

import java.util.List;

public interface IApoointmentService {

    List<Appointment> getAllAppointments(int ownerId);

    MessageResponseDTO addAppointment(AddAppointmentRequestDTO addAppointmentRequestDTO, int ownerId);
}
