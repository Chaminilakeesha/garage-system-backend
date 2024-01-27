package com.example.garagesystembackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",nullable = false)
    private VehicleOwner vehicleOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id",nullable = false)
    private Vehicle vehicle;

    @Column(name = "a_date", nullable = false)
    private LocalDate date;

    @Column(name = "a_time", nullable = false)
    private LocalTime time;

    @Column(name = "description")
    private String description;

    @Column(name = "is_approved")
    private boolean isApproved;

}
