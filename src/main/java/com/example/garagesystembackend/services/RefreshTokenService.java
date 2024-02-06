package com.example.garagesystembackend.services;

import com.example.garagesystembackend.DTO.requests.RefreshTokenRequestDTO;
import com.example.garagesystembackend.DTO.responses.JwtResponseDTO;
import com.example.garagesystembackend.DTO.responses.RefreshTokenResponseDTO;
import com.example.garagesystembackend.models.RefreshToken;
import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.repositories.RefreshTokenRepository;
import com.example.garagesystembackend.repositories.VehicleOwnerRepository;
import com.example.garagesystembackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public RefreshToken generateRefreshToken(int ownerId) {
        RefreshToken refreshToken = refreshTokenRepository.findByVehicleOwnerOwnerId(ownerId).orElse(null);
        if(refreshToken != null){
            return refreshToken;
        }
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByOwnerId(ownerId);
        refreshToken = new RefreshToken(
                vehicleOwner,
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusDays(1));
        return refreshTokenRepository.save(refreshToken);
    }

//    public void validateRefreshToken(String token) {
//        refreshTokenRepository.findByRefreshToken(token)
//                .orElseThrow(() -> new GarageSystemException("Invalid refresh Token"));
//    }
//
//    public void deleteRefreshToken(String token) {
//        refreshTokenRepository.deleteByRefreshToken(token);
//    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(refreshToken.getRefreshToken() + " Refresh token is expired. Please make a new login..!");
        }
        return refreshToken;
    }

    public RefreshTokenResponseDTO getRefreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenRepository.findByRefreshToken(refreshTokenRequestDTO.getRefreshToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getVehicleOwner)
                .map(vehicleOwner -> {
                    String accessToken = jwtUtils.generateToken(vehicleOwner.getEmail());
                    return new RefreshTokenResponseDTO(accessToken, refreshTokenRequestDTO.getRefreshToken());
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }


}
