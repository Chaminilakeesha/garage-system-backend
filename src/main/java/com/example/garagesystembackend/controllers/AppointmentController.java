package com.example.garagesystembackend.controllers;

import com.example.garagesystembackend.DTO.requests.BookAppointmentRequestDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.kafka.producer.KafkaProducer;
import com.example.garagesystembackend.models.Appointment;
import com.example.garagesystembackend.models.TimeSlot;
import com.example.garagesystembackend.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/reserve/{ownerId}")
    public MessageResponseDTO bookAppointment(@RequestBody BookAppointmentRequestDTO bookAppointmentRequestDTO, @PathVariable int ownerId){
        return appointmentService.bookAppointment(bookAppointmentRequestDTO,ownerId);
    }

    @GetMapping("/all/{ownerId}")
    public List<Appointment> getAllAppointments(@PathVariable int ownerId){
        return appointmentService.getAllAppointments(ownerId);
    }

    @GetMapping("/timeslots")
    public List<TimeSlot> getAllTimeSlots(){
        return appointmentService.getAllTimeSlots();
    }



}
