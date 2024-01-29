package com.example.garagesystembackend.repositories;

import com.example.garagesystembackend.models.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner,Integer>, JpaSpecificationExecutor<VehicleOwner> {
    public VehicleOwner findByEmail(String Email);
    public VehicleOwner findByOwnerId(int ownerId);

    boolean existsByEmail(String email);
}