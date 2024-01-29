package com.example.garagesystembackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    public VehicleOwner(String ownerName, String email, String mobileNo, String password) {
        this.ownerName = ownerName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "vehicleOwner",fetch = FetchType.LAZY)
    private List<Vehicle> vehicles;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicleOwner",fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @JsonIgnore
    @OneToOne(mappedBy = "vehicleOwner",fetch = FetchType.LAZY)
    private PasswordResetToken passwordResetToken;

    public VehicleOwner(int ownerId, String ownerName, String email, String mobileNo, String password) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
    }
}
