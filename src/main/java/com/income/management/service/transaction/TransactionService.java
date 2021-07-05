package com.income.management.service.transaction;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.GenericTransaction;
import com.income.management.model.Transaction;
import com.income.management.model.TransferTransaction;
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
    public void createSpent(TransactionDTO spent) {
        try {
            this.transactionRepo.createSpentTransaction(spent.getName(), spent.getValue(), spent.getAccount(), spent.getCategory());
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
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

    public void deleteSpent(long id) {
        try {
            this.transactionRepo.deleteTransaction(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
    }

    //-----Revenue -----
    public void createRevenue(TransactionDTO revenue) {
        try {
            this.transactionRepo.createRevenueTransaction(revenue.getName(), revenue.getValue(), revenue.getAccount(), revenue.getCategory());
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
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
            return this.transactionRepo.findAllRevenues();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteRevenue(long id) {
        try {
            this.transactionRepo.deleteTransaction(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
    }

    //-----Transfer -----
    public void createTransfer(TransferDTO transfer) {
        try {
            this.transactionRepo.createTransferTransaction(
                    transfer.getName(),
                    transfer.getValue(),
                    transfer.getAccount_in(),
                    transfer.getAccount_out(),
                    transfer.getCategory()
                    );
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
    }

    public TransferTransaction findTransfer(long id) {
        try {
            return this.transactionRepo.findTransfer(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TransferTransaction> findAllTransfers() {
        try {
            return this.transactionRepo.findAllTransfer();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteTransfer(long id) {
        try {
            this.transactionRepo.deleteTransaction(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
    }

}
