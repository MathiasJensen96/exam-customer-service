package com.example.CustomerService.controller;

import com.example.CustomerService.model.entity.Address;
import com.example.CustomerService.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/new")
    public ResponseEntity<Address> createAddress(@RequestParam("address") String address) {
        return ResponseEntity.ok(addressService.createAddress(address));
    }
}
