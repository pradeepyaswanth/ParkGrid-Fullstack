package com.parkgrid.billing.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BillRequest {

    @NotNull
    private Long bookingId;

    @NotNull
    private Long userId;

    @NotBlank
    private String vehicleNumber;

    @Min(1)
    private double hourlyRate;

    @Min(1)
    private long durationHours;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public long getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(long durationHours) {
        this.durationHours = durationHours;
    }
}