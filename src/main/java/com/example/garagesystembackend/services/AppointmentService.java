package com.example.garagesystembackend.services;

import com.example.garagesystembackend.repositories.AppointmentRepository;
import com.example.garagesystembackend.services.interfaces.IApoointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IApoointmentService {

    private AppointmentRepository appointmentRepository;
}
