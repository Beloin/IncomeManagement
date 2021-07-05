package com.income.management.service.transaction;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.GenericTransaction;
import com.income.management.model.Transaction;
import com.income.management.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<GenericTransaction> findAllSpends() {
        try {
            return this.transactionRepo.findAllSpents();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
