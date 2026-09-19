package com.parkgrid.booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkgrid.booking.client.BillingClient;
import com.parkgrid.booking.client.ParkingClient;
import com.parkgrid.booking.dto.BillRequest;
import com.parkgrid.booking.dto.BookingRequest;
import com.parkgrid.booking.dto.BookingResponse;
import com.parkgrid.booking.dto.ParkingSlotResponse;
import com.parkgrid.booking.model.Booking;
import com.parkgrid.booking.model.BookingStatus;
import com.parkgrid.booking.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ParkingClient parkingClient;
    private final BillingClient billingClient;

    public BookingService(
            BookingRepository bookingRepository,
            ParkingClient parkingClient,
            BillingClient billingClient) {

        this.bookingRepository = bookingRepository;
        this.parkingClient = parkingClient;
        this.billingClient = billingClient;
    }

    // CREATE BOOKING
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {

        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new RuntimeException(
                    "End time must be after start time");
        }

        // 1. Check slot with Parking Service
        ParkingSlotResponse slot =
                parkingClient.getSlot(request.getSlotId());

        if (!"AVAILABLE".equals(slot.getStatus())) {
            throw new RuntimeException(
                    "Parking slot is not available");
        }

        // 2. Check for overlapping bookings
        List<Booking> conflicts =
                bookingRepository.findConflictingBookings(
                        request.getSlotId(),
                        request.getStartTime(),
                        request.getEndTime(),
                        BookingStatus.CONFIRMED
                );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException(
                    "Parking slot is already booked");
        }

        // 3. Create booking
        Booking booking = new Booking(
                request.getUserId(),
                request.getParkingId(),
                request.getSlotId(),
                request.getVehicleNumber(),
                request.getStartTime(),
                request.getEndTime()
        );

        Booking saved =
                bookingRepository.save(booking);

        // 4. Reserve parking slot
        parkingClient.updateSlot(
                request.getSlotId(),
                "RESERVED"
        );

        // 5. Calculate parking duration
        long durationHours =
                java.time.Duration.between(
                        request.getStartTime(),
                        request.getEndTime()
                ).toHours();

        // Minimum 1 hour
        if (durationHours < 1) {
            durationHours = 1;
        }

        // 6. Create billing request
        BillRequest billRequest =
                new BillRequest(
                        saved.getId(),
                        request.getUserId(),
                        request.getVehicleNumber(),
                        50.0,
                        durationHours
                );

        // 7. Send request to Billing Service
        billingClient.createBill(billRequest);

        return convertToResponse(saved);
    }

    // GET BOOKING BY ID
    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        return convertToResponse(booking);
    }

    // GET ALL BOOKINGS OF USER
    public List<BookingResponse> getBookingsByUser(Long userId) {

        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // CANCEL BOOKING
    public BookingResponse cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        // Only confirmed bookings can be cancelled
        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new RuntimeException(
                    "Booking cannot be cancelled");
        }

        // Change booking status
        booking.setStatus(BookingStatus.CANCELLED);

        // Save cancellation
        Booking updated =
                bookingRepository.save(booking);

        // Release parking slot
        parkingClient.updateSlot(
                booking.getSlotId(),
                "AVAILABLE"
        );

        return convertToResponse(updated);
    }

    // COMPLETE BOOKING
    public BookingResponse completeBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new RuntimeException(
                    "Booking cannot be completed");
        }

        booking.setStatus(BookingStatus.COMPLETED);

        Booking updated =
                bookingRepository.save(booking);

        // Release parking slot
        parkingClient.updateSlot(
                booking.getSlotId(),
                "AVAILABLE"
        );

        return convertToResponse(updated);
    }

    // CONVERT ENTITY TO RESPONSE
    private BookingResponse convertToResponse(Booking booking) {

        return new BookingResponse(
                booking.getId(),
                booking.getUserId(),
                booking.getParkingId(),
                booking.getSlotId(),
                booking.getVehicleNumber(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
    }
}