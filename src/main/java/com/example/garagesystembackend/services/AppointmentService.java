package com.example.garagesystembackend.services;

import com.example.garagesystembackend.DTO.requests.BookAppointmentRequestDTO;
import com.example.garagesystembackend.DTO.responses.AppointmentStatusResponseDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.kafka.producer.KafkaProducer;
import com.example.garagesystembackend.models.Appointment;
import com.example.garagesystembackend.models.TimeSlot;
import com.example.garagesystembackend.models.Vehicle;
import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.repositories.AppointmentRepository;
import com.example.garagesystembackend.repositories.TimeSlotRepository;
import com.example.garagesystembackend.repositories.VehicleOwnerRepository;
import com.example.garagesystembackend.repositories.VehicleRepository;
import com.example.garagesystembackend.services.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private VehicleRepository VehicleRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public List<Appointment> getAllAppointments(int ownerId) {
        return appointmentRepository.findAllByVehicleOwnerOwnerId(ownerId);
    }

    @Override
    public MessageResponseDTO bookAppointment(BookAppointmentRequestDTO bookAppointmentRequestDTO, int ownerId) {
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByOwnerId(ownerId);
        TimeSlot timeSlot = timeSlotRepository.findBySlotId(bookAppointmentRequestDTO.getSlotId());
        Vehicle vehicle = VehicleRepository.findByVehicleId(bookAppointmentRequestDTO.getVehicleId());
        Appointment appointment = new Appointment(
                vehicleOwner,
                vehicle,
                timeSlot,
                bookAppointmentRequestDTO.getDate(),
                bookAppointmentRequestDTO.getDescription(),
                null
        );
        appointment = appointmentRepository.save(appointment);
        kafkaProducer.sendAppointment("appointments",appointment);
        return new MessageResponseDTO("success","Appointment request made successfully");
    }

    @Override
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    @Override
    public void updateAppointmentStatus(AppointmentStatusResponseDTO appointmentStatusResponseDTO) {
        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentStatusResponseDTO.getAppointmentId());
        appointment.setStatus(appointmentStatusResponseDTO.getStatus());
        appointmentRepository.save(appointment);
    }

}
