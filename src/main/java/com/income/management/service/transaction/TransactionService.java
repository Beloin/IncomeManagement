package com.income.management.service.transaction;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.GenericTransaction;
import com.income.management.model.Transaction;
import com.income.management.repository.TransactionRepository;
import com.income.management.resources.transaction.dto.TransactionDTO;
import com.income.management.resources.transaction.dto.TransferDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    //-----Spend -----
    public GenericTransaction createSpent(TransactionDTO spent){
        return ;
    }

    public GenericTransaction findSpend(long id) {
        try {
            return this.transactionRepo.findSpent(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GenericTransaction> findAllSpends() {
        try {
            return this.transactionRepo.findAllSpents();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteSpent(long id){
        return;
    }

    //-----Revenue -----
    public GenericTransaction createRevenue(TransactionDTO revenue){
        return ;
    }

    public GenericTransaction findRevenue(long id) {
        try {
            return this.transactionRepo.findRevenue(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GenericTransaction> findAllRevenues() {
        try {
            return this.transactionRepo.findAllRevenue();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteRevenue(long id){
        return;
    }

    //-----Transfer -----
    public GenericTransaction createTransfer(TransferDTO transfer){
        return ;
    }

    public GenericTransaction findTransfer(long id) {
        try {
            return this.transactionRepo.findTransfer(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GenericTransaction> findAllTransfers() {
        try {
            return this.transactionRepo.findAllTransfer();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteTransfer(long id){
        return;
    }

}
