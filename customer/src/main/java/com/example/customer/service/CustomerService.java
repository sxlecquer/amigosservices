package com.example.customer.service;

import com.example.customer.entity.Customer;
import com.example.customer.model.CustomerRegistrationRequest;
import com.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: validate email
        customerRepository.save(customer);
    }
}
