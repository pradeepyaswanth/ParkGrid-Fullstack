package com.parkgrid.billing.dto;

import java.time.LocalDateTime;

import com.parkgrid.billing.model.PaymentStatus;

public class BillResponse {

    private Long id;
    private Long bookingId;
    private Long userId;
    private String vehicleNumber;
    private double hourlyRate;
    private long durationHours;
    private double amount;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;

    public BillResponse(Long id, Long bookingId, Long userId,
                        String vehicleNumber, double hourlyRate,
                        long durationHours, double amount,
                        PaymentStatus paymentStatus,
                        LocalDateTime createdAt) {

        this.id = id;
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleNumber = vehicleNumber;
        this.hourlyRate = hourlyRate;
        this.durationHours = durationHours;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
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

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}