package com.parkgrid.admin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParkingRequest {

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Total slots are required")
    @Min(value = 1, message = "Total slots must be at least 1")
    private Integer totalSlots;

    public ParkingRequest() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(Integer totalSlots) {
        this.totalSlots = totalSlots;
    }
}