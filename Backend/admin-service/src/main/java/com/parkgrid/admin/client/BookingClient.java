package com.parkgrid.admin.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.parkgrid.admin.dto.BookingResponse;

@FeignClient(name = "booking-service")
public interface BookingClient {

    @GetMapping("/api/bookings/{id}")
    BookingResponse getBooking(
            @PathVariable("id") Long id
    );


    @GetMapping("/api/bookings/user/{userId}")
    List<BookingResponse> getBookingsByUser(
            @PathVariable("userId") Long userId
    );
}