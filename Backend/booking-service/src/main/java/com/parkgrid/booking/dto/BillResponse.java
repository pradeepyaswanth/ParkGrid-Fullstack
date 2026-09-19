package com.parkgrid.booking.dto;

public class BillResponse {

    private Long id;
    private Long bookingId;
    private double amount;
    private String paymentStatus;

    public BillResponse() {
    }

    public Long getId() {
        return id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}