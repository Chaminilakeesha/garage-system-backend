package com.example.garagesystembackend.repositories;

import com.example.garagesystembackend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer>, JpaSpecificationExecutor<Vehicle> {
}
