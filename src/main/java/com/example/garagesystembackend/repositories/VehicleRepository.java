package com.example.garagesystembackend.repositories;

import com.example.garagesystembackend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer>, JpaSpecificationExecutor<Vehicle> {

    public List<Vehicle> findAllByVehicleOwnerOwnerId(int ownerId);

    public Vehicle findByVehicleId(int vehicleId);


}
