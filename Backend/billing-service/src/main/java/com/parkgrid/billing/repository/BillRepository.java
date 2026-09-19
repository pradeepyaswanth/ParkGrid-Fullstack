package com.parkgrid.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkgrid.billing.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByUserId(Long userId);

    List<Bill> findByBookingId(Long bookingId);
}