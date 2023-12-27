package com.example.CustomerService.controller;

import com.example.CustomerService.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrderController {
    private final CustomerOrderService customerOrderService;

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseOrderByCustomerId(@RequestHeader Map<String, String> header) {
        String customerId = header.get("role_id");
        return ResponseEntity.ok(customerOrderService.purchaseOrder(customerId));
    }
}
