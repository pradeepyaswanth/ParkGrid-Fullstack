package com.parkgrid.client.dto;

public class ParkingResponse {

    private Long id;
    private String location;
    private Integer totalSlots;
    private Integer availableSlots;

    public ParkingResponse() {
    }

    public ParkingResponse(
            Long id,
            String location,
            Integer totalSlots,
            Integer availableSlots) {

        this.id = id;
        this.location = location;
        this.totalSlots = totalSlots;
        this.availableSlots = availableSlots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }
}