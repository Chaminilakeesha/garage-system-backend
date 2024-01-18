package com.example.garagesystembackend.services.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface IVehicleOwnerService {

    String getVehicleOwner(String email);

    String updateVehicleOwner(String name);
}
