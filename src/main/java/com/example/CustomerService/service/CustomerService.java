package com.example.CustomerService.service;

import com.example.CustomerService.model.CustomerRegistrationRequest;
import com.example.CustomerService.model.dto.CustomerDTO;
import com.example.CustomerService.model.entity.Customer;
import com.example.CustomerService.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.CustomerService.model.enums.Topic;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerDTO registerCustomer(CustomerRegistrationRequest request) {

        //TODO: gRPC call to address service to find addressId

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .addressId(request.addressId())
                .role("1")
                .password(request.password())
                .build();

        Customer newCustomer = customerRepository.save(customer);

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName(newCustomer.getFirstName())
                .lastName(newCustomer.getLastName())
                .email(newCustomer.getEmail())
                .addressId(newCustomer.getAddressId())
                .phone(newCustomer.getPhone())
                .build();

        return customerDTO;
    }

    public List<CustomerDTO> getCustomers() {
        List<CustomerDTO> customers = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            customers.add(new CustomerDTO(customer));
        }
        return customers;
    }

    public CustomerDTO getCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return new CustomerDTO(customer);
    }

    public CustomerDTO updateCustomer(int id, Customer customerRequest) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        if (customerRequest.getFirstName() != null) {
            if (!customer.getFirstName().equals(customerRequest.getFirstName())) {
                customer.setFirstName(customerRequest.getFirstName());
            }
        }
        if (customerRequest.getLastName() != null) {
            if (!customer.getLastName().equals(customerRequest.getLastName())) {
                customer.setLastName(customerRequest.getLastName());
            }
        }
        //TODO: This should have some kind of check as it is a unique value
        if (customerRequest.getEmail() != null) {
            if (!customer.getEmail().equals(customerRequest.getEmail())) {
                customer.setEmail(customerRequest.getEmail());
            }
        }
        if (customerRequest.getAddressId() != 0) {
            if (customer.getAddressId() != customerRequest.getAddressId()) {
                customer.setAddressId(customerRequest.getAddressId());
            }
        }
        //TODO: This should have some kind of check as it is a unique value
        if (customerRequest.getPhone() != null) {
            if (!customer.getPhone().equals(customerRequest.getPhone())) {
                customer.setPhone(customer.getPhone());
            }
        }

        Customer newCustomer = customerRepository.save(customer);

        return new CustomerDTO(newCustomer);
    }

    public Map<String, Boolean> deleteCustomer(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        customerRepository.delete(customer);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }

    public void notifyCustomer(CustomerDTO customer, Topic kafkaTopic) {
        String subject = "";
        String messageBody = "";

        switch (kafkaTopic) {
            case new_order_placed:
                subject = "MTOGO: New order has been placed";
                messageBody = "";
                break;
            case order_accepted:
                subject = "MTOGO: Order has been accepted";
                messageBody = "";
                break;
            case order_ready:
                subject = "MTOGO: Order is ready for pickup";
                messageBody = "";
                break;
            case order_cancelled:
                subject = "MTOGO: Order has been cancelled";
                messageBody = "";
                break;
            case order_picked_up:
                subject = "MTOGO: Order has been picked up";
                messageBody = "";
                break;
            case order_delivered:
                subject = "MTOGO: Order has been delivered";
                messageBody = "";
                break;
            case order_claimed:
                subject = "MTOGO: Order has been claimed";
                messageBody = "";
                break;
            default:
                throw new RuntimeException("An error occurred in customer service when listening on kafka topic: {}" + kafkaTopic);
        }
    }
}