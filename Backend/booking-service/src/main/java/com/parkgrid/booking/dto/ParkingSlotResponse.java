package com.parkgrid.booking.dto;

public class ParkingSlotResponse {

    private Long id;
    private Long parkingId;
    private String slotNumber;
    private String status;

    public ParkingSlotResponse() {
    }

    public Long getId() {
        return id;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public String getStatus() {
        return status;
    }
}