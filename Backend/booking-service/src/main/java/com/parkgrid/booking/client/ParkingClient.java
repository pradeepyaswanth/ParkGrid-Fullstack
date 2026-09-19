package com.parkgrid.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parkgrid.booking.dto.ParkingSlotResponse;

@FeignClient(name = "parking-service")
public interface ParkingClient {

    @GetMapping("/api/parking/slots/{slotId}")
    ParkingSlotResponse getSlot(
            @PathVariable Long slotId);

    @PutMapping("/api/parking/slots/{slotId}")
    ParkingSlotResponse updateSlot(
            @PathVariable Long slotId,
            @RequestParam String status);
}