package com.parkgrid.client.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parkgrid.client.client.BillingClient;
import com.parkgrid.client.client.BookingClient;
import com.parkgrid.client.client.ParkingClient;
import com.parkgrid.client.dto.BillResponse;
import com.parkgrid.client.dto.BookingRequest;
import com.parkgrid.client.dto.BookingResponse;
import com.parkgrid.client.dto.ParkingResponse;
import com.parkgrid.client.dto.ParkingSlotResponse;

@Service
public class ClientService {

    private final ParkingClient parkingClient;
    private final BookingClient bookingClient;
    private final BillingClient billingClient;


    public ClientService(
            ParkingClient parkingClient,
            BookingClient bookingClient,
            BillingClient billingClient) {

        this.parkingClient = parkingClient;
        this.bookingClient = bookingClient;
        this.billingClient = billingClient;
    }


    // ==========================================
    // PARKING
    // ==========================================

    public List<ParkingResponse> getAllParking() {

        return parkingClient.getAllParking();
    }


    public ParkingResponse getParkingById(Long id) {

        return parkingClient.getParking(id);
    }


    public List<ParkingSlotResponse> getAvailableSlots(
            Long parkingId) {

        return parkingClient.getAvailableSlots(parkingId);
    }


    // ==========================================
    // BOOKING
    // ==========================================

    public BookingResponse createBooking(
            BookingRequest request) {

        return bookingClient.createBooking(request);
    }


    public BookingResponse getBooking(Long id) {

        return bookingClient.getBooking(id);
    }


    public List<BookingResponse> getUserBookings(
            Long userId) {

        return bookingClient.getBookingsByUser(userId);
    }


    public BookingResponse cancelBooking(Long id) {

        return bookingClient.cancelBooking(id);
    }


    public BookingResponse completeBooking(Long id) {

        return bookingClient.completeBooking(id);
    }


    // ==========================================
    // BILLING
    // ==========================================

    public BillResponse getBill(Long id) {

        return billingClient.getBill(id);
    }


    public List<BillResponse> getUserBills(
            Long userId) {

        return billingClient.getBillsByUser(userId);
    }
}