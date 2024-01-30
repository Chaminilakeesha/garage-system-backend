package com.example.garagesystembackend.services;

import com.example.garagesystembackend.DTO.requests.AddVehicleRequestDTO;
import com.example.garagesystembackend.DTO.requests.UpdateVehicleRequestDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.models.Vehicle;
import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.repositories.VehicleOwnerRepository;
import com.example.garagesystembackend.repositories.VehicleRepository;
import com.example.garagesystembackend.services.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleOwnerRepository vehicleOwnerRepository;

    @Override
    public MessageResponseDTO addVehicle(AddVehicleRequestDTO addVehicleRequestDTO, int ownerId) {
        VehicleOwner vehicleOwner = vehicleOwnerRepository.findByOwnerId(ownerId);
        Vehicle vehicle = new Vehicle(addVehicleRequestDTO.getVehicleNo(), addVehicleRequestDTO.getModel(),vehicleOwner);
        vehicleRepository.save(vehicle);
        return new MessageResponseDTO("success","Vehicle added successfully");
    }

    @Override
    public List<Vehicle> getAllVehicles(int ownerId) {
        return vehicleRepository.findAllByVehicleOwnerOwnerId(ownerId);
    }

    @Override
    public MessageResponseDTO deleteVehicle(int vehicleId) {
        Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId);
        vehicleRepository.delete(vehicle);
        return new MessageResponseDTO("success","Vehicle deleted successfully");
    }

    @Override
    public MessageResponseDTO updateVehicle(UpdateVehicleRequestDTO updateVehicleRequestDTO) {
        Vehicle selectedVehicle = vehicleRepository.findByVehicleId(updateVehicleRequestDTO.getVehicleId());
        Vehicle vehicle = new Vehicle(
                updateVehicleRequestDTO.getVehicleId(),
                updateVehicleRequestDTO.getVehicleNo(),
                updateVehicleRequestDTO.getModel(),
                selectedVehicle.getVehicleOwner());
        vehicleRepository.save(vehicle);
        return new MessageResponseDTO("success","Vehicle updated successfully");
    }
}
