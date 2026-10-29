package com.parkgrid.client.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.parkgrid.client.dto.BookingRequest;
import com.parkgrid.client.dto.BookingResponse;

@FeignClient(name = "booking-service")
public interface BookingClient {

    @PostMapping("/api/bookings")
    BookingResponse createBooking(
            @RequestBody BookingRequest request);


    @GetMapping("/api/bookings/{id}")
    BookingResponse getBooking(
            @PathVariable("id") Long id);


    @GetMapping("/api/bookings/user/{userId}")
    List<BookingResponse> getBookingsByUser(
            @PathVariable("userId") Long userId);


    @PutMapping("/api/bookings/{id}/cancel")
    BookingResponse cancelBooking(
            @PathVariable("id") Long id);


    @PutMapping("/api/bookings/{id}/complete")
    BookingResponse completeBooking(
            @PathVariable("id") Long id);
}