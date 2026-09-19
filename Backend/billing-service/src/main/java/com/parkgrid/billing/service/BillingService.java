package com.parkgrid.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parkgrid.billing.dto.BillRequest;
import com.parkgrid.billing.dto.BillResponse;
import com.parkgrid.billing.model.Bill;
import com.parkgrid.billing.model.PaymentStatus;
import com.parkgrid.billing.repository.BillRepository;

@Service
public class BillingService {

    private final BillRepository billRepository;

    public BillingService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public BillResponse createBill(BillRequest request) {

        double amount =
                request.getHourlyRate() * request.getDurationHours();

        Bill bill = new Bill(
                request.getBookingId(),
                request.getUserId(),
                request.getVehicleNumber(),
                request.getHourlyRate(),
                request.getDurationHours(),
                amount
        );

        Bill saved = billRepository.save(bill);

        return convertToResponse(saved);
    }

    public BillResponse getBillById(Long id) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Bill not found"));

        return convertToResponse(bill);
    }

    public List<BillResponse> getBillsByUser(Long userId) {

        return billRepository.findByUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public BillResponse updatePaymentStatus(
            Long id, PaymentStatus status) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Bill not found"));

        bill.setPaymentStatus(status);

        Bill updated = billRepository.save(bill);

        return convertToResponse(updated);
    }

    public void deleteBill(Long id) {

        if (!billRepository.existsById(id)) {
            throw new RuntimeException("Bill not found");
        }

        billRepository.deleteById(id);
    }

    private BillResponse convertToResponse(Bill bill) {

        return new BillResponse(
                bill.getId(),
                bill.getBookingId(),
                bill.getUserId(),
                bill.getVehicleNumber(),
                bill.getHourlyRate(),
                bill.getDurationHours(),
                bill.getAmount(),
                bill.getPaymentStatus(),
                bill.getCreatedAt()
        );
    }
}