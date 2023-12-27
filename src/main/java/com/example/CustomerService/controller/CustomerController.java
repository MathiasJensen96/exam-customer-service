package com.example.CustomerService.controller;

import com.example.CustomerService.model.CustomerRegistrationRequest;
import com.example.CustomerService.model.dto.CustomerDTO;
import com.example.CustomerService.model.entity.Customer;
import com.example.CustomerService.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/registration")
    @Transactional
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        return ResponseEntity.ok(customerService.registerCustomer(customerRegistrationRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable(value = "id") int id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}