package com.example.garagesystembackend.controllers;

import com.example.garagesystembackend.DTO.requests.AddVehicleRequestDTO;
import com.example.garagesystembackend.DTO.requests.UpdateVehicleRequestDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.models.Vehicle;
import com.example.garagesystembackend.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add/{ownerId}")
    public MessageResponseDTO addVehicle(@RequestBody AddVehicleRequestDTO addVehicleRequestDTO, @PathVariable int ownerId){
        return vehicleService.addVehicle(addVehicleRequestDTO,ownerId);
    }

    @GetMapping("/all/{ownerId}")
    public List<Vehicle> getAllVehicles(@PathVariable int ownerId){
        return vehicleService.getAllVehicles(ownerId);
    }

    @PostMapping("/update")
    public MessageResponseDTO updateVehicle(@RequestBody UpdateVehicleRequestDTO updateVehicleRequestDTO){
        return vehicleService.updateVehicle(updateVehicleRequestDTO);
    }

    @DeleteMapping("/delete/{vehicleId}")
    public MessageResponseDTO deleteVehicle(@PathVariable int vehicleId){
        return vehicleService.deleteVehicle(vehicleId);
    }
}