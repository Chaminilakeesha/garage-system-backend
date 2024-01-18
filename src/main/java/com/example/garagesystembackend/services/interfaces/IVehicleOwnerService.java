package com.example.garagesystembackend.services.interfaces;

import com.example.garagesystembackend.models.VehicleOwner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IVehicleOwnerService {

    List<VehicleOwner> getAllVehicleOwners();

    VehicleOwner getVehicleOwner(String email);

    void updateVehicleOwner(VehicleOwner owner);
}
