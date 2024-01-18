package com.example.garagesystembackend.models;

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
@Table(name = "Vehicle_Owner")
public class VehicleOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "mobile_no",nullable = false)
    private String mobileNo;

    @Column(name="password",nullable = false)
    private String password;

    @OneToMany(mappedBy = "vehicleOwner")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "vehicleOwner")
    private List<Appointment> appointments;

}
