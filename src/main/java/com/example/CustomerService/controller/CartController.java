package com.example.CustomerService.controller;

import com.example.CustomerService.model.dto.CartDTO;
import com.example.CustomerService.model.entity.Cart;
import com.example.CustomerService.service.CustomerOrderService;
import com.example.CustomerService.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/carts", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CartController {
    private final CustomerOrderService customerOrderService;

    @PostMapping("/add-items")
    public ResponseEntity<CartDTO> addItemToCart(@RequestHeader Map<String, String> header, @RequestBody Cart cart) {
        String customerId = header.get("role_id");

        return ResponseEntity.ok(customerOrderService.addItemToCart(customerId, cart));
    }
}
