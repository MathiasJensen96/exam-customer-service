package com.example.CustomerService.service;

import com.example.CustomerService.model.entity.Address;
import com.example.CustomerService.model.entity.Customer;
import com.example.CustomerService.model.enums.OrderStatus;
import com.example.CustomerService.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    OrderStatus orderStatus;

    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    public Customer registerCustomer(String firstName, String lastName, String email, String phone, String address, String password) {
        String[] splitted = address.split("\\s+");
        int addressId = customerRepository.getAddressId(splitted[0], Integer.parseInt(splitted[1]));

        Customer customer = new Customer(firstName, lastName, email, phone, addressId, "Customer", password);
        return customerRepository.save(customer);
    }
}