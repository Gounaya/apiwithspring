package com.game.apiwithspring.controller;

import com.game.apiwithspring.exception.ResourceNotFoundException;
import com.game.apiwithspring.model.Customer;
import com.game.apiwithspring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable(value = "id") Long CustomerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(CustomerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found on :: "+ CustomerId));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers/add")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable(value = "id") Long customerId,
            @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found on :: "+ customerId));

        customer.setEmailId(customerDetails.getEmailId());
        customer.setLastName(customerDetails.getLastName());
        customer.setFirstName(customerDetails.getFirstName());
        final Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(
            @PathVariable(value = "id") Long CustomerId) throws Exception {
        Customer customer = customerRepository.findById(CustomerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found on :: "+ CustomerId));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

