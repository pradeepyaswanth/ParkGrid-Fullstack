package com.parkgrid.client.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkgrid.client.dto.BillResponse;
import com.parkgrid.client.dto.BookingRequest;
import com.parkgrid.client.dto.BookingResponse;
import com.parkgrid.client.dto.ParkingResponse;
import com.parkgrid.client.dto.ParkingSlotResponse;
import com.parkgrid.client.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;


    public ClientController(ClientService clientService) {

        this.clientService = clientService;
    }


    // ==========================================
    // PARKING
    // ==========================================

    @GetMapping("/parking")
    public ResponseEntity<List<ParkingResponse>> getParking() {

        return ResponseEntity.ok(
                clientService.getAllParking()
        );
    }


    @GetMapping("/parking/{id}")
    public ResponseEntity<ParkingResponse> getParkingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clientService.getParkingById(id)
        );
    }


    @GetMapping("/parking/{parkingId}/slots")
    public ResponseEntity<List<ParkingSlotResponse>> getAvailableSlots(
            @PathVariable Long parkingId) {

        return ResponseEntity.ok(
                clientService.getAvailableSlots(parkingId)
        );
    }


    // ==========================================
    // BOOKINGS
    // ==========================================

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> createBooking(
            @RequestBody BookingRequest request) {

        return ResponseEntity.ok(
                clientService.createBooking(request)
        );
    }


    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingResponse> getBooking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clientService.getBooking(id)
        );
    }


    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getUserBookings(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                clientService.getUserBookings(userId)
        );
    }


    @PutMapping("/bookings/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clientService.cancelBooking(id)
        );
    }


    @PutMapping("/bookings/{id}/complete")
    public ResponseEntity<BookingResponse> completeBooking(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clientService.completeBooking(id)
        );
    }


    // ==========================================
    // BILLING
    // ==========================================

    @GetMapping("/billing/{id}")
    public ResponseEntity<BillResponse> getBill(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clientService.getBill(id)
        );
    }


    @GetMapping("/billing/user/{userId}")
    public ResponseEntity<List<BillResponse>> getUserBills(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                clientService.getUserBills(userId)
        );
    }
}