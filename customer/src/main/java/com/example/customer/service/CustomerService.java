package com.example.customer.service;

import com.example.clients.fraud.FraudClient;
import com.example.clients.notification.NotificationRequest;
import com.example.customer.entity.Customer;
import com.example.customer.model.CustomerRegistrationRequest;
import com.example.clients.fraud.FraudCheckHistoryResponse;
import com.example.customer.repository.CustomerRepository;
import com.example.kafka.KafkaProducer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, KafkaProducer producer) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: validate email
        customerRepository.saveAndFlush(customer);
        FraudCheckHistoryResponse fraudCheckHistoryResponse = fraudClient.isFraudster(customer.getId());
        if(Objects.requireNonNull(fraudCheckHistoryResponse).isFraudster()) {
            throw new IllegalStateException("This customer is a fraudster");
        }

        producer.produceNotification(new NotificationRequest(customer.getId(), customer.getEmail(),
                        String.format("Hello %s, welcome to amigosservices!", customer.getFirstName()))
        );
    }
}
