package com.parkgrid.parking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    // CREATE PARKING
    @PostMapping
    public ResponseEntity<ParkingResponse> createParking(
            @Valid @RequestBody ParkingRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parkingService.createParking(request));
    }

    // GET ALL PARKING
    @GetMapping
    public ResponseEntity<List<ParkingResponse>> getAllParking() {

        return ResponseEntity.ok(
                parkingService.getAllParking());
    }

    // GET PARKING BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ParkingResponse> getParking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                parkingService.getParkingById(id));
    }

    // GET ONE SLOT BY ID
    // Used by Booking Service
    @GetMapping("/slots/{slotId}")
    public ResponseEntity<ParkingSlot> getSlot(
            @PathVariable Long slotId) {

        return ResponseEntity.ok(
                parkingService.getSlotById(slotId));
    }

    // GET AVAILABLE SLOTS FOR A PARKING
    @GetMapping("/{parkingId}/slots")
    public ResponseEntity<List<ParkingSlot>> getAvailableSlots(
            @PathVariable Long parkingId) {

        return ResponseEntity.ok(
                parkingService.getAvailableSlots(parkingId));
    }

    // UPDATE SLOT STATUS
    // Used by Booking Service
    @PutMapping("/slots/{slotId}")
    public ResponseEntity<ParkingSlot> updateSlotStatus(
            @PathVariable Long slotId,
            @RequestParam SlotStatus status) {

        return ResponseEntity.ok(
                parkingService.updateSlotStatus(
                        slotId,
                        status));
    }

    // DELETE PARKING
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParking(
            @PathVariable Long id) {

        parkingService.deleteParking(id);

        return ResponseEntity.ok(
                "Parking deleted successfully");
    }
}