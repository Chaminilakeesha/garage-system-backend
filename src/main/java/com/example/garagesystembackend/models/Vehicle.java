package com.example.garagesystembackend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int vehicleId;

    @Column(name = "vehicle_no",nullable = false,unique = true)
    private String vehicleNo;

    @Column(name="model",nullable = false)
    private String model;

    @ManyToOne
    @JoinColumn(name = "Owner_id",nullable = false)
    private VehicleOwner vehicleOwner;

    @OneToMany(mappedBy = "vehicle")
    private List<Appointment> appointments;


}
