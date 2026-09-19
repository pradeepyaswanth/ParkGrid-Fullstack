package com.parkgrid.booking.dto;

import java.time.LocalDateTime;

import com.parkgrid.booking.model.BookingStatus;

public class BookingResponse {

    private Long id;
    private Long userId;
    private Long parkingId;
    private Long slotId;
    private String vehicleNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status;
    private LocalDateTime createdAt;

    public BookingResponse(
            Long id,
            Long userId,
            Long parkingId,
            Long slotId,
            String vehicleNumber,
            LocalDateTime startTime,
            LocalDateTime endTime,
            BookingStatus status,
            LocalDateTime createdAt) {

        this.id = id;
        this.userId = userId;
        this.parkingId = parkingId;
        this.slotId = slotId;
        this.vehicleNumber = vehicleNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}