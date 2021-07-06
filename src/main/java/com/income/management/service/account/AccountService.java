package com.income.management.service.account;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.account.AccountWithValue;
import com.income.management.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private final AccountRepository accountRepo;

    public AccountService(AccountRepository accountRepo){this.accountRepo = accountRepo;}


    public boolean createAccount(String name) {
        try {
            return this.accountRepo.createAccount(name);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeAccountName(long id, String newName) {
        try {
            return this.accountRepo.changeAccountName(id, newName);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AccountWithValue> findAccounts() {
        try {
            return this.accountRepo.findAccounts();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAccount(long id) {
        try {
            return this.accountRepo.deleteAccount(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
