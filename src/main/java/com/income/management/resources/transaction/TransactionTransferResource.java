package com.income.management.resources.transaction;

import com.income.management.model.GenericTransaction;
import com.income.management.model.TransferTransaction;
import com.income.management.resources.transaction.dto.TransactionDTO;
import com.income.management.resources.transaction.dto.TransferDTO;
import com.income.management.service.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class TransactionTransferResource {

    private final TransactionService transactionService;

    public TransactionTransferResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions/transfers")
    public void createTransfer(@Valid @RequestBody TransferDTO transfer) {
        this.transactionService.createTransfer(transfer);
    }

    @GetMapping("/transactions/transfers/{id}")
    public TransferTransaction getTransfer(@PathVariable(value = "id") long id) {
        return this.transactionService.findTransfer(id);
    }

    @GetMapping("/transactions/transfers")
    public List<TransferTransaction> getAllTransfers() {
        return this.transactionService.findAllTransfers();
    }

    @DeleteMapping("/transactions/transfers/{id}")
    public void deleteTransfer(@PathVariable(value = "id") long id) {
        this.transactionService.deleteTransfer(id);
    }

}
