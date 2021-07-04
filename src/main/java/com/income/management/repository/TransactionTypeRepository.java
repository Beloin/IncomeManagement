package com.income.management.repository;

import com.income.management.conf.DatabaseConfig;
import com.income.management.exception.GenericTransactionException;
import com.income.management.model.TransactionType;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionTypeRepository implements ITransactionTypeRepository {
    public TransactionTypeRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    final DatabaseConfig databaseConfig;


    @Override
    public List<TransactionType> findAll() throws GenericTransactionException {
        List<TransactionType> types = new ArrayList<>();
        String query = "SELECT * FROM TransactionType";

        try {
            var con = databaseConfig.getConnection();
            var stm = con.createStatement();
            var rsSet = stm.executeQuery(query);

            types = this.transactionTypeMapper(rsSet);

            rsSet.close();
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return types;
    }

    private List<TransactionType> transactionTypeMapper(ResultSet resultSet) throws SQLException {
        List<TransactionType> types = new ArrayList<>();
        while (resultSet.next())
            types.add(new TransactionType(resultSet.getLong("id"), resultSet.getString("typeName")));

        return types;
    }

    @Override
    public TransactionType save(TransactionType transactionType) throws GenericTransactionException {
        String query = "INSERT INTO TransactionType (name) VALUES (?)";

        return transactionType;
    }

    @Override
    public TransactionType find(long id) {
        return null;
    }

    @Override
    public boolean update(TransactionType transactionType) {
        return false;
    }

    @Override
    public boolean delete(TransactionType transactionType) {
        return false;
    }
}
