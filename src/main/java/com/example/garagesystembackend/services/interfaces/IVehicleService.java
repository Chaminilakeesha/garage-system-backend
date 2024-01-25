package com.example.garagesystembackend.services.interfaces;

import com.example.garagesystembackend.DTO.requests.AddVehicleRequestDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.models.Vehicle;

import java.util.List;

public interface IVehicleService {

    MessageResponseDTO addVehicle(AddVehicleRequestDTO addVehicleRequestDTO, int ownerId);

    List<Vehicle> getAllVehicles(int ownerId);

    MessageResponseDTO deleteVehicle(int vehicleId);
}
