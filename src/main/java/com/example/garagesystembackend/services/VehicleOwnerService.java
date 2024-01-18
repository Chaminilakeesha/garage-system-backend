package com.example.garagesystembackend.services;

import com.example.garagesystembackend.services.interfaces.IVehicleOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VehicleOwnerService implements IVehicleOwnerService {




    public String getVehicleOwner(String email){
        return email;
    }

    public String updateVehicleOwner(String name){
        return name;
    }

}
