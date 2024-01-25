package com.example.garagesystembackend.controllers;

import com.example.garagesystembackend.DTO.requests.UpdateVehicleOwnerDTO;
import com.example.garagesystembackend.DTO.responses.MessageResponseDTO;
import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.services.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicleOwner")
public class VehicleOwnerController {

    @Autowired
    private VehicleOwnerService vehicleOwnerService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/{ownerId}")
    public VehicleOwner getVehicleOwner(@PathVariable int ownerId){
        return vehicleOwnerService.getVehicleOwner(ownerId);
    }
    @PostMapping("/{ownerId}")
    public MessageResponseDTO updateVehicleOwner(@RequestBody UpdateVehicleOwnerDTO updateVehicleOwnerDTO, @PathVariable int ownerId){
        return vehicleOwnerService.updateVehicleOwner(updateVehicleOwnerDTO,ownerId);
    }


}
