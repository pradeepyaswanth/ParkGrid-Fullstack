package com.parkgrid.parking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_slots")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parkingId;

    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    public ParkingSlot() {
    }

    public ParkingSlot(Long parkingId, String slotNumber) {
        this.parkingId = parkingId;
        this.slotNumber = slotNumber;
        this.status = SlotStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
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

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }
}