package com.parkgrid.client.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.parkgrid.client.dto.ParkingResponse;
import com.parkgrid.client.dto.ParkingSlotResponse;

@FeignClient(name = "parking-service")
public interface ParkingClient {

    @GetMapping("/api/parking")
    List<ParkingResponse> getAllParking();


    @GetMapping("/api/parking/{id}")
    ParkingResponse getParking(
            @PathVariable("id") Long id);


    @GetMapping("/api/parking/{parkingId}/slots")
    List<ParkingSlotResponse> getAvailableSlots(
            @PathVariable("parkingId") Long parkingId);
}