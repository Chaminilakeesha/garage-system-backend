package com.example.garagesystembackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Owner_id",nullable = false)
    private VehicleOwner vehicleOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle",fetch = FetchType.LAZY)
    private List<Appointment> appointments;


    public Vehicle(String vehicleNo, String model, VehicleOwner vehicleOwner) {
        this.vehicleNo = vehicleNo;
        this.model = model;
        this.vehicleOwner = vehicleOwner;
    }

    public Vehicle(int vehicleId, String vehicleNo, String model, VehicleOwner vehicleOwner) {
        this.vehicleId = vehicleId;
        this.vehicleNo = vehicleNo;
        this.model = model;
        this.vehicleOwner = vehicleOwner;
    }
}
