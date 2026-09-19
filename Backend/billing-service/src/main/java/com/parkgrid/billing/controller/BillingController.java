package com.parkgrid.billing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parkgrid.billing.dto.BillRequest;
import com.parkgrid.billing.dto.BillResponse;
import com.parkgrid.billing.model.PaymentStatus;
import com.parkgrid.billing.service.BillingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping
    public ResponseEntity<BillResponse> createBill(
            @Valid @RequestBody BillRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(billingService.createBill(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getBill(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billingService.getBillById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BillResponse>> getBillsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                billingService.getBillsByUser(userId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BillResponse> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {

        return ResponseEntity.ok(
                billingService.updatePaymentStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(
            @PathVariable Long id) {

        billingService.deleteBill(id);

        return ResponseEntity.ok(
                "Bill deleted successfully");
    }
}