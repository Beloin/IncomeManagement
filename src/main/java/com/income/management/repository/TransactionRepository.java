package com.income.management.repository;

import com.income.management.conf.DatabaseConfig;
import com.income.management.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {


    private final DatabaseConfig dbConfig;

    public TransactionRepository(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public List<Transaction> findAllSpents() {
        String query = ("SELECT tr.id, tr.trans_value, tr.date FROM Transaction where userAccountOutIn = NULL");
        this.dbConfig.getConnection()
        return null;
    }
}
