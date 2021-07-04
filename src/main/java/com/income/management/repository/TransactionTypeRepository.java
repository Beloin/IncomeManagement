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
        String query = "SELECT * FROM TransactionType";

        return getTransactionTypesPerQuery(query);
    }

    private List<TransactionType> transactionTypeMapper(ResultSet resultSet) throws SQLException {
        List<TransactionType> types = new ArrayList<>();
        while (resultSet.next())
            types.add(new TransactionType(
                            resultSet.getLong("id"),
                            resultSet.getString("typeName"),
                            resultSet.getDate("createAt")
                    )
            );

        return types;
    }

    @Override
    public TransactionType save(TransactionType transactionType) throws GenericTransactionException {
        var date = new java.util.Date();
        var sqlDate = new java.sql.Date(date.getTime());
        String query = String.format("INSERT INTO TransactionType (typeName, createAt) VALUES (\"%s\", \"%s\")",
                transactionType.getName(), sqlDate);

        try {
            var con = this.databaseConfig.getConnection();
            var stm = con.createStatement();

            stm.execute(query);
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }


        return new TransactionType(this.getMaxId(), transactionType.getName(), sqlDate);
    }

    private long getMaxId() throws GenericTransactionException {
        String query = "SELECT MAX(id) FROM TransactionType";
        long id;
        try {
            var con = this.databaseConfig.getConnection();
            var stm = con.createStatement();
            var rsSet = stm.executeQuery(query);

            rsSet.next();
            id = rsSet.getLong(1);

            rsSet.close();
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return id;
    }

    @Override
    public TransactionType find(long id) throws GenericTransactionException {
        List<TransactionType> types = new ArrayList<>();
        String query = "SELECT * FROM TransactionType as trTp WHERE trTp.id =" + id;

        types = getTransactionTypesPerQuery(query);

        return types.get(0);
    }

    /**
     * Get TransactionTypes over a query.
     *
     * @param query
     * @return List<TransactionType>
     * @throws GenericTransactionException
     */
    private List<TransactionType> getTransactionTypesPerQuery(String query) throws GenericTransactionException {
        List<TransactionType> types;
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

    @Override
    public boolean update(TransactionType transactionType) {
        return false;
    }

    @Override
    public void delete(TransactionType transactionType) throws GenericTransactionException {
        String query = "DELETE FROM TransactionType tr WHERE tr.id=" + transactionType.getId();

        try {
            var con = databaseConfig.getConnection();
            var stm = con.createStatement();

            stm.execute(query);

            stm.close();
            con.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }
}
