package com.example.garagesystembackend.services.interfaces;

import com.example.garagesystembackend.DTO.requests.BookAppointmentRequestDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.models.Appointment;
import com.example.garagesystembackend.models.TimeSlot;

import java.util.List;

public interface IApoointmentService {

    List<Appointment> getAllAppointments(int ownerId);

    MessageResponseDTO bookAppointment(BookAppointmentRequestDTO bookAppointmentRequestDTO, int ownerId);

    List<TimeSlot> getAllTimeSlots();
}
