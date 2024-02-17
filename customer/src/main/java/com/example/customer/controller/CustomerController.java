package com.example.customer.controller;

import com.example.customer.model.CustomerRegistrationRequest;
import com.example.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
public record CustomerController(CustomerService customerService) {
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        log.info("new customer registration: {}", request);
        customerService.registerCustomer(request);
    }
}
