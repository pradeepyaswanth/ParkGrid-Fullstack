package com.parkgrid.admin.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.parkgrid.admin.dto.ParkingRequest;
import com.parkgrid.admin.dto.ParkingResponse;
import com.parkgrid.admin.dto.ParkingSlotResponse;

@FeignClient(name = "parking-service")
public interface ParkingClient {

    @PostMapping("/api/parking")
    ParkingResponse createParking(
            @RequestBody ParkingRequest request
    );


    @GetMapping("/api/parking")
    List<ParkingResponse> getAllParking();


    @GetMapping("/api/parking/{id}")
    ParkingResponse getParking(
            @PathVariable("id") Long id
    );


    @GetMapping("/api/parking/{parkingId}/slots")
    List<ParkingSlotResponse> getAvailableSlots(
            @PathVariable("parkingId") Long parkingId
    );


    @GetMapping("/api/parking/slots/{slotId}")
    ParkingSlotResponse getSlot(
            @PathVariable("slotId") Long slotId
    );


    @PutMapping("/api/parking/slots/{slotId}")
    ParkingSlotResponse updateSlotStatus(
            @PathVariable("slotId") Long slotId,
            @RequestParam("status") String status
    );


    @DeleteMapping("/api/parking/{id}")
    String deleteParking(
            @PathVariable("id") Long id
    );
}