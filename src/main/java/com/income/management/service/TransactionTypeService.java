package com.income.management.service;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.TransactionType;
import com.income.management.repository.ITransactionTypeRepository;
import com.income.management.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionTypeService {

    final ITransactionTypeRepository repository;

    public TransactionTypeService(ITransactionTypeRepository repository) {
        this.repository = repository;
    }

    public List<TransactionType> findAll() {
        List<TransactionType> res;
        try {
            res = repository.findAll();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
            return new ArrayList<TransactionType>();
        }

        return res;
    }
}
