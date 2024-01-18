package com.example.garagesystembackend.controllers;

import com.example.garagesystembackend.services.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicleOwner")
public class VehicleOwnerController {

    @Autowired
    private VehicleOwnerService vehicleOwnerService;

    @GetMapping("/{email}")
    public String getVehicleOwner(@PathVariable String email){
        return vehicleOwnerService.getVehicleOwner(email);
    }

    @PostMapping("/{id}")
    public String updateVehicleOwner(@RequestBody String name){
        return vehicleOwnerService.updateVehicleOwner(name);
    }
}
