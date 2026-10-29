package com.parkgrid.client.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.parkgrid.client.dto.BillResponse;

@FeignClient(name = "billing-service")
public interface BillingClient {

    @GetMapping("/api/billing/{id}")
    BillResponse getBill(
            @PathVariable("id") Long id);


    @GetMapping("/api/billing/user/{userId}")
    List<BillResponse> getBillsByUser(
            @PathVariable("userId") Long userId);
}