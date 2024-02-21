package com.example.customer.client;

import com.example.customer.model.FraudCheckHistoryResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/v1/fraud-check")
public interface FraudClient {
    @GetExchange("/{customerId}")
    FraudCheckHistoryResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
