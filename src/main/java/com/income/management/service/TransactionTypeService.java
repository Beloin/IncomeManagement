package com.income.management.service;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.TransactionType;
import com.income.management.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TransactionTypeService {

    final TransactionTypeRepository repository;

    public TransactionTypeService(TransactionTypeRepository repository) {
        this.repository = repository;
    }

    public List<TransactionType> findAll() {
        List<TransactionType> res = null;
        try {
            res = repository.findAll();
            System.out.println(res.toString());
        } catch (GenericTransactionException e) {
            e.printStackTrace();
            return new ArrayList<TransactionType>();
        }

        return res;
    }
}
