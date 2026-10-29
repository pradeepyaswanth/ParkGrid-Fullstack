package com.parkgrid.admin.dto;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}