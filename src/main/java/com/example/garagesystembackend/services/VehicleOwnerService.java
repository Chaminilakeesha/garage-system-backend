package com.example.garagesystembackend.services;

import com.example.garagesystembackend.models.VehicleOwner;
import com.example.garagesystembackend.repositories.VehicleOwnerRepository;
import com.example.garagesystembackend.services.interfaces.IVehicleOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VehicleOwnerService implements IVehicleOwnerService {

    private VehicleOwnerRepository vehicleOwnerRepository;

    public List<VehicleOwner> getAllVehicleOwners(){
        return vehicleOwnerRepository.findAll();
    }

    public VehicleOwner getVehicleOwner(String email){
        return vehicleOwnerRepository.findByEmail(email);
    }

    public void updateVehicleOwner(VehicleOwner owner){
        vehicleOwnerRepository.save(owner);
    }

}
