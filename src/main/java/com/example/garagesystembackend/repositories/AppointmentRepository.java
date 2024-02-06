package com.example.garagesystembackend.repositories;

import com.example.garagesystembackend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer>, JpaSpecificationExecutor<Appointment> {

    public List<Appointment> findAllByVehicleOwnerOwnerId(int ownerId);

    public Appointment findByAppointmentId(int appointmentId);
    boolean existsByVehicleVehicleId(int vehicleId);

    List<Appointment> findAllByVehicleOwnerOwnerIdAndStatus(int ownerId, String status);
}
