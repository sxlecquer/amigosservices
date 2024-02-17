package com.example.customer.service;

import com.example.customer.entity.Customer;
import com.example.customer.model.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService() {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: validate email and save customer to db
    }
}
