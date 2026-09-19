package com.parkgrid.parking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parkgrid.parking.dto.ParkingRequest;
import com.parkgrid.parking.dto.ParkingResponse;
import com.parkgrid.parking.model.ParkingSlot;
import com.parkgrid.parking.model.SlotStatus;
import com.parkgrid.parking.service.ParkingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    // Create parking
    @PostMapping
    public ResponseEntity<ParkingResponse> createParking(
            @Valid @RequestBody ParkingRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parkingService.createParking(request));
    }

    // Get all parking
    @GetMapping
    public ResponseEntity<List<ParkingResponse>> getAllParking() {

        return ResponseEntity.ok(
                parkingService.getAllParking());
    }

    // Get parking by ID
    @GetMapping("/{id}")
    public ResponseEntity<ParkingResponse> getParking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                parkingService.getParkingById(id));
    }

    // Get available slots
    @GetMapping("/{parkingId}/slots")
    public ResponseEntity<List<ParkingSlot>> getAvailableSlots(
            @PathVariable Long parkingId) {

        return ResponseEntity.ok(
                parkingService.getAvailableSlots(parkingId));
    }

    // Update slot status
    @PutMapping("/slots/{slotId}")
    public ResponseEntity<ParkingSlot> updateSlotStatus(
            @PathVariable Long slotId,
            @RequestParam SlotStatus status) {

        return ResponseEntity.ok(
                parkingService.updateSlotStatus(
                        slotId,
                        status));
    }

    // Delete parking
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParking(
            @PathVariable Long id) {

        parkingService.deleteParking(id);

        return ResponseEntity.ok(
                "Parking deleted successfully");
    }
}