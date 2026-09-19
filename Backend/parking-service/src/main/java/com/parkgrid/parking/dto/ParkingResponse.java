package com.parkgrid.parking.dto;

public class ParkingResponse {

    private Long id;
    private String location;
    private int totalSlots;
    private int availableSlots;

    public ParkingResponse(
            Long id,
            String location,
            int totalSlots,
            int availableSlots) {

        this.id = id;
        this.location = location;
        this.totalSlots = totalSlots;
        this.availableSlots = availableSlots;
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }
}