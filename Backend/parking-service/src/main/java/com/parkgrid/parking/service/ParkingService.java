package com.parkgrid.parking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parkgrid.parking.dto.ParkingRequest;
import com.parkgrid.parking.dto.ParkingResponse;
import com.parkgrid.parking.model.Parking;
import com.parkgrid.parking.model.ParkingSlot;
import com.parkgrid.parking.model.SlotStatus;
import com.parkgrid.parking.repository.ParkingRepository;
import com.parkgrid.parking.repository.ParkingSlotRepository;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingSlotRepository slotRepository;

    public ParkingService(
            ParkingRepository parkingRepository,
            ParkingSlotRepository slotRepository) {

        this.parkingRepository = parkingRepository;
        this.slotRepository = slotRepository;
    }

    // CREATE PARKING
    public ParkingResponse createParking(ParkingRequest request) {

        Parking parking = new Parking(
                request.getLocation(),
                request.getTotalSlots()
        );

        Parking saved = parkingRepository.save(parking);

        // Automatically create slots
        for (int i = 1; i <= request.getTotalSlots(); i++) {

            ParkingSlot slot = new ParkingSlot(
                    saved.getId(),
                    "S-" + i
            );

            slotRepository.save(slot);
        }

        return convertToResponse(saved);
    }

    // GET ALL PARKING LOCATIONS
    public List<ParkingResponse> getAllParking() {

        return parkingRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET PARKING BY ID
    public ParkingResponse getParkingById(Long id) {

        Parking parking = parkingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Parking not found"));

        return convertToResponse(parking);
    }

    // GET ONE SLOT BY ID
    // Used by Booking Service
    public ParkingSlot getSlotById(Long slotId) {

        return slotRepository.findById(slotId)
                .orElseThrow(() ->
                        new RuntimeException("Slot not found"));
    }

    // GET AVAILABLE SLOTS
    public List<ParkingSlot> getAvailableSlots(Long parkingId) {

        return slotRepository.findByParkingIdAndStatus(
                parkingId,
                SlotStatus.AVAILABLE
        );
    }

    // UPDATE SLOT STATUS
    // Used by Booking Service
    public ParkingSlot updateSlotStatus(
            Long slotId,
            SlotStatus status) {

        ParkingSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() ->
                        new RuntimeException("Slot not found"));

        SlotStatus oldStatus = slot.getStatus();

        slot.setStatus(status);

        ParkingSlot updated =
                slotRepository.save(slot);

        updateAvailableCount(
                slot.getParkingId(),
                oldStatus,
                status
        );

        return updated;
    }

    // UPDATE AVAILABLE SLOT COUNT
    private void updateAvailableCount(
            Long parkingId,
            SlotStatus oldStatus,
            SlotStatus newStatus) {

        if (oldStatus == newStatus) {
            return;
        }

        Parking parking = parkingRepository.findById(parkingId)
                .orElseThrow(() ->
                        new RuntimeException("Parking not found"));

        // AVAILABLE → RESERVED/OCCUPIED
        if (oldStatus == SlotStatus.AVAILABLE
                && newStatus != SlotStatus.AVAILABLE) {

            parking.setAvailableSlots(
                    parking.getAvailableSlots() - 1
            );
        }

        // RESERVED/OCCUPIED → AVAILABLE
        if (oldStatus != SlotStatus.AVAILABLE
                && newStatus == SlotStatus.AVAILABLE) {

            parking.setAvailableSlots(
                    parking.getAvailableSlots() + 1
            );
        }

        parkingRepository.save(parking);
    }

    // DELETE PARKING
    public void deleteParking(Long id) {

        if (!parkingRepository.existsById(id)) {

            throw new RuntimeException(
                    "Parking not found");
        }

        List<ParkingSlot> slots =
                slotRepository.findByParkingId(id);

        slotRepository.deleteAll(slots);

        parkingRepository.deleteById(id);
    }

    // CONVERT PARKING TO RESPONSE
    private ParkingResponse convertToResponse(
            Parking parking) {

        return new ParkingResponse(
                parking.getId(),
                parking.getLocation(),
                parking.getTotalSlots(),
                parking.getAvailableSlots()
        );
    }
}