package com.income.management.repository;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.TransactionType;

import java.util.List;

public interface ITransactionTypeRepository {
    public List<TransactionType> findAll() throws GenericTransactionException;

    public TransactionType save(TransactionType transactionType) throws GenericTransactionException;

    public TransactionType find(long id) throws GenericTransactionException;

    public boolean update(TransactionType transactionType) throws GenericTransactionException;

    public boolean delete(TransactionType transactionType) throws GenericTransactionException;

}
