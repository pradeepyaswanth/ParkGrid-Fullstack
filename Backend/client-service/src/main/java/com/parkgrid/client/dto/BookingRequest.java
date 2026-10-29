package com.parkgrid.client.dto;

import java.time.LocalDateTime;

public class BookingRequest {

    private Long userId;
    private Long parkingId;
    private Long slotId;
    private String vehicleNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}