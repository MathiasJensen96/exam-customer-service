package com.example.CustomerService.model;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        int addressId,
        String phone,
        String password
) {
}
