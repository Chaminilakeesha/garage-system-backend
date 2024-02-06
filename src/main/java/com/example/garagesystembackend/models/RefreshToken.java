package com.example.garagesystembackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private int refreshTokenId;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false,unique = true)
    private VehicleOwner vehicleOwner;

    @Column(name="refresh_token",nullable = false, unique = true)
    private String refreshToken;

    @Column(name="expiry_date",nullable = false)
    private LocalDateTime expiryDate;

    public RefreshToken(VehicleOwner vehicleOwner, String string, LocalDateTime expiryDate) {
          this.vehicleOwner = vehicleOwner;
          this.refreshToken = string;
          this.expiryDate = expiryDate;
    }
}