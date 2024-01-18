package com.example.garagesystembackend.controllers;

import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.services.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicleOwner")
public class VehicleOwnerController {

    @Autowired
    private VehicleOwnerService vehicleOwnerService;

    @GetMapping("/")
    public List<VehicleOwner> getAllVehicleOwners(){
        return vehicleOwnerService.getAllVehicleOwners();
    }

    @GetMapping("/{email}")
    public VehicleOwner getVehicleOwner(@PathVariable String email){
        return vehicleOwnerService.getVehicleOwner(email);
    }

    @PostMapping("/")
    public void updateVehicleOwner(@RequestBody VehicleOwner owner){
        vehicleOwnerService.updateVehicleOwner(owner);
    }


}
