package com.parkgrid.parking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkgrid.parking.model.ParkingSlot;
import com.parkgrid.parking.model.SlotStatus;

public interface ParkingSlotRepository
        extends JpaRepository<ParkingSlot, Long> {

    List<ParkingSlot> findByParkingId(Long parkingId);

    List<ParkingSlot> findByParkingIdAndStatus(
            Long parkingId,
            SlotStatus status);

    Optional<ParkingSlot> findByParkingIdAndSlotNumber(
            Long parkingId,
            String slotNumber);
}