package com.example.garagesystembackend.repositories;

import com.example.garagesystembackend.models.RefreshToken;
import com.example.garagesystembackend.models.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    Optional<RefreshToken> findByVehicleOwnerOwnerId(int ownerId);

}
