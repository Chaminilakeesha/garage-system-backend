package com.example.garagesystembackend.repositories;

import com.example.garagesystembackend.models.PasswordResetToken;
import com.example.garagesystembackend.models.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Integer>, JpaSpecificationExecutor<PasswordResetToken> {
    public PasswordResetToken findByToken(String token);

    public PasswordResetToken findByVehicleOwner(VehicleOwner vehicleOwner);

    boolean existsByVehicleOwner(VehicleOwner vehicleOwner);

}