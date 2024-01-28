package com.example.garagesystembackend.repositories;

import com.example.garagesystembackend.models.Appointment;
import com.example.garagesystembackend.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer>, JpaSpecificationExecutor<TimeSlot> {

    public List<TimeSlot> findAll();

    public TimeSlot findBySlotId(int slotId);
}