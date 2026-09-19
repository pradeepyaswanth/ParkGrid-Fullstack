package com.parkgrid.booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkgrid.booking.dto.BookingRequest;
import com.parkgrid.booking.dto.BookingResponse;
import com.parkgrid.booking.model.Booking;
import com.parkgrid.booking.model.BookingStatus;
import com.parkgrid.booking.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public BookingResponse createBooking(BookingRequest request) {

        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new RuntimeException(
                    "End time must be after start time");
        }

        List<Booking> conflicts =
                bookingRepository.findConflictingBookings(
                        request.getSlotId(),
                        request.getStartTime(),
                        request.getEndTime(),
                        BookingStatus.CONFIRMED
                );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException(
                    "Parking slot is already booked for this time");
        }

        Booking booking = new Booking(
                request.getUserId(),
                request.getParkingId(),
                request.getSlotId(),
                request.getVehicleNumber(),
                request.getStartTime(),
                request.getEndTime()
        );

        Booking saved = bookingRepository.save(booking);

        return convertToResponse(saved);
    }

    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        return convertToResponse(booking);
    }

    public List<BookingResponse> getBookingsByUser(Long userId) {

        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public BookingResponse cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);

        Booking updated = bookingRepository.save(booking);

        return convertToResponse(updated);
    }

    public BookingResponse completeBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.COMPLETED);

        Booking updated = bookingRepository.save(booking);

        return convertToResponse(updated);
    }

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