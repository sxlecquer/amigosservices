package com.example.fraud.controller;

import com.example.fraud.model.FraudCheckHistoryResponse;
import com.example.fraud.service.FraudCheckHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
public record FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {
    @GetMapping("/{customerId}")
    public FraudCheckHistoryResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        return new FraudCheckHistoryResponse(fraudCheckHistoryService.isFraudulentCustomer(customerId));
    }
}
