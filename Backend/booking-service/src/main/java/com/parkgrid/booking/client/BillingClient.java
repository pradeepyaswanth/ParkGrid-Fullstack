package com.parkgrid.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.parkgrid.booking.dto.BillRequest;
import com.parkgrid.booking.dto.BillResponse;

@FeignClient(name = "billing-service")
public interface BillingClient {

    @PostMapping("/api/billing")
    BillResponse createBill(
            @RequestBody BillRequest request);
}