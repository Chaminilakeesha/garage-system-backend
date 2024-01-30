package com.example.garagesystembackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int tokenId;

    @Column(name = "token", nullable = false)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false,unique = true)
    private VehicleOwner vehicleOwner;

    @Column(name = "expiration", nullable = false)
    private LocalDateTime expiration;

    public PasswordResetToken(String token, VehicleOwner vehicleOwner, LocalDateTime expiration) {
        this.token = token;
        this.vehicleOwner = vehicleOwner;
        this.expiration = expiration;
    }
}
