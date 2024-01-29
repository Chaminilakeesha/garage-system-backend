package com.example.garagesystembackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int appointmentId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",nullable = false)
    private VehicleOwner vehicleOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id",nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id",nullable = false)
    private TimeSlot timeSlot;

    @Column(name = "date",nullable = false)
    private String date;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    public Appointment(VehicleOwner vehicleOwner, Vehicle vehicle, TimeSlot timeSlot, String date, String description, String status) {
        this.vehicleOwner = vehicleOwner;
        this.vehicle = vehicle;
        this.timeSlot = timeSlot;
        this.date = date;
        this.description = description;
        this.status = status;
    }

}
