package com.income.management.resources.transaction;

import com.income.management.model.GenericTransaction;
import com.income.management.resources.transaction.dto.TransactionDTO;
import com.income.management.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class TransactionSpendResource {

    private final TransactionService transactionService;

    public TransactionSpendResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions/spents")
    public GenericTransaction createSpent(@Valid @RequestBody TransactionDTO spent) {
        return this.transactionService.createSpent(spent);
    }

    @GetMapping("/transactions/spents/{id}")
    public GenericTransaction getSpent(@PathVariable(value = "id") long id) {
        return this.transactionService.findSpend(id);
    }

    @GetMapping("/transactions/spents")
    public List<GenericTransaction> getAllSpents() {
        return this.transactionService.findAllSpends();
    }

    @DeleteMapping("/transactions/spents/{id}")
    public void deleteSpent(@PathVariable(value = "id") long id) {
        this.transactionService.deleteSpent(id);
        return;
    }

}
