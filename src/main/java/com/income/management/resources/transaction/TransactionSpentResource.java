package com.income.management.resources.transaction;

import com.income.management.model.Transaction;
import com.income.management.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class TransactionSpentResource {

    private final TransactionService transactionService;

    public TransactionSpentResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/spents")
    public List<Transaction> getSpent() {
        return this.transactionService.findAllSpends();
    }
}
