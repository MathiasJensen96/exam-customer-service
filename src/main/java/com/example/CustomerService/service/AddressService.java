package com.example.CustomerService.service;

import com.example.CustomerService.model.entity.Address;
import com.example.CustomerService.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public Address createAddress(String address) {
        //TODO: Check if address exists before inserting into db
        String[] splitted = address.split("\\s+");
        String street = splitted[0];
        int number = Integer.parseInt(splitted[1]);

        Address newAddress = new Address(street, number);
        return addressRepository.save(newAddress);
    }
}