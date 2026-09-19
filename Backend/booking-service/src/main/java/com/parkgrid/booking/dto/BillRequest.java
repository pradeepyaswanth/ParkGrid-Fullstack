package com.parkgrid.booking.dto;

public class BillRequest {

    private Long bookingId;
    private Long userId;
    private String vehicleNumber;
    private double hourlyRate;
    private long durationHours;

    public BillRequest() {
    }

    public BillRequest(Long bookingId,
                       Long userId,
                       String vehicleNumber,
                       double hourlyRate,
                       long durationHours) {

        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleNumber = vehicleNumber;
        this.hourlyRate = hourlyRate;
        this.durationHours = durationHours;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public long getDurationHours() {
        return durationHours;
    }
}