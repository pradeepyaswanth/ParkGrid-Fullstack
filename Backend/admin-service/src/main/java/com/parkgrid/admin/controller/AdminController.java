package com.parkgrid.admin.controller;

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

import com.parkgrid.admin.dto.BillResponse;
import com.parkgrid.admin.dto.BookingResponse;
import com.parkgrid.admin.dto.ParkingRequest;
import com.parkgrid.admin.dto.ParkingResponse;
import com.parkgrid.admin.dto.ParkingSlotResponse;
import com.parkgrid.admin.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService) {

        this.adminService = adminService;
    }


    // ==========================================
    // PARKING
    // ==========================================

    @PostMapping("/parking")
    public ResponseEntity<ParkingResponse> createParking(
            @Valid @RequestBody ParkingRequest request) {

        ParkingResponse response =
                adminService.createParking(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping("/parking")
    public ResponseEntity<List<ParkingResponse>> getAllParking() {

        return ResponseEntity.ok(
                adminService.getAllParking()
        );
    }


    @GetMapping("/parking/{id}")
    public ResponseEntity<ParkingResponse> getParking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.getParking(id)
        );
    }


    @GetMapping("/parking/{parkingId}/slots")
    public ResponseEntity<List<ParkingSlotResponse>> getAvailableSlots(
            @PathVariable Long parkingId) {

        return ResponseEntity.ok(
                adminService.getAvailableSlots(parkingId)
        );
    }


    @GetMapping("/parking/slots/{slotId}")
    public ResponseEntity<ParkingSlotResponse> getSlot(
            @PathVariable Long slotId) {

        return ResponseEntity.ok(
                adminService.getSlot(slotId)
        );
    }


    @PutMapping("/parking/slots/{slotId}")
    public ResponseEntity<ParkingSlotResponse> updateSlotStatus(
            @PathVariable Long slotId,
            @RequestParam String status) {

        return ResponseEntity.ok(
                adminService.updateSlotStatus(
                        slotId,
                        status
                )
        );
    }


    @DeleteMapping("/parking/{id}")
    public ResponseEntity<String> deleteParking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.deleteParking(id)
        );
    }


    // ==========================================
    // BOOKINGS
    // ==========================================

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingResponse> getBooking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.getBooking(id)
        );
    }


    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                adminService.getBookingsByUser(userId)
        );
    }


    // ==========================================
    // BILLING
    // ==========================================

    @GetMapping("/billing/{id}")
    public ResponseEntity<BillResponse> getBill(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.getBill(id)
        );
    }


    @GetMapping("/billing/user/{userId}")
    public ResponseEntity<List<BillResponse>> getBillsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                adminService.getBillsByUser(userId)
        );
    }
}