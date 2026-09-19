package com.parkgrid.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkgrid.parking.model.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
}