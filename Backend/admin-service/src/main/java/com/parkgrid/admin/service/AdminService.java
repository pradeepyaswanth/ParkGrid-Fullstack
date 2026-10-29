package com.parkgrid.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parkgrid.admin.client.BillingClient;
import com.parkgrid.admin.client.BookingClient;
import com.parkgrid.admin.client.ParkingClient;
import com.parkgrid.admin.dto.BillResponse;
import com.parkgrid.admin.dto.BookingResponse;
import com.parkgrid.admin.dto.ParkingRequest;
import com.parkgrid.admin.dto.ParkingResponse;
import com.parkgrid.admin.dto.ParkingSlotResponse;

@Service
public class AdminService {

    private final ParkingClient parkingClient;
    private final BookingClient bookingClient;
    private final BillingClient billingClient;


    public AdminService(
            ParkingClient parkingClient,
            BookingClient bookingClient,
            BillingClient billingClient) {

        this.parkingClient = parkingClient;
        this.bookingClient = bookingClient;
        this.billingClient = billingClient;
    }


    // ==========================================
    // PARKING MANAGEMENT
    // ==========================================

    public ParkingResponse createParking(
            ParkingRequest request) {

        return parkingClient.createParking(request);
    }


    public List<ParkingResponse> getAllParking() {

        return parkingClient.getAllParking();
    }


    public ParkingResponse getParking(Long id) {

        return parkingClient.getParking(id);
    }


    public List<ParkingSlotResponse> getAvailableSlots(
            Long parkingId) {

        return parkingClient.getAvailableSlots(parkingId);
    }


    public ParkingSlotResponse getSlot(Long slotId) {

        return parkingClient.getSlot(slotId);
    }


    public ParkingSlotResponse updateSlotStatus(
            Long slotId,
            String status) {

        return parkingClient.updateSlotStatus(
                slotId,
                status
        );
    }


    public String deleteParking(Long id) {

        return parkingClient.deleteParking(id);
    }


    // ==========================================
    // BOOKING MANAGEMENT
    // ==========================================

    public BookingResponse getBooking(Long id) {

        return bookingClient.getBooking(id);
    }


    public List<BookingResponse> getBookingsByUser(
            Long userId) {

        return bookingClient.getBookingsByUser(userId);
    }


    // ==========================================
    // BILLING MANAGEMENT
    // ==========================================

    public BillResponse getBill(Long id) {

        return billingClient.getBill(id);
    }


    public List<BillResponse> getBillsByUser(
            Long userId) {

        return billingClient.getBillsByUser(userId);
    }
}