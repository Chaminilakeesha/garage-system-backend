package com.example.garagesystembackend.services;

import com.example.garagesystembackend.repositories.VehicleRepository;
import com.example.garagesystembackend.services.interfaces.IVehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements IVehicleService {

    private VehicleRepository vehicleRepository;

}
