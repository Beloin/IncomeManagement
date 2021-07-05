package com.income.management.repository;

import com.income.management.conf.DatabaseConfig;
import com.income.management.exception.GenericTransactionException;
import com.income.management.model.GenericTransaction;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {


    private final DatabaseConfig dbConfig;

    public TransactionRepository(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public List<GenericTransaction> findAllSpents() throws GenericTransactionException {
        String query = ("SELECT tr.id, tr.transValue, tr.transDate, tr.userAccountOutId " +
                "FROM Transaction as tr where tr.userAccountInId = NULL");
        List<GenericTransaction> trans = new ArrayList<>();

        try {
            var con = this.dbConfig.getConnection();
            var stm = con.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next()) trans.add(new GenericTransaction(rs.getLong("id"),
                    rs.getLong("userAccountOutId"),
                    rs.getFloat("trans_value"), rs.getDate("transDate")));


            rs.close();
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return trans;
    }

    public void createSpentTransaction(String name, float transValue, long accountOut, long category)
            throws GenericTransactionException {
        String query = String.format("INSERT INTO Transaction " +
                        "(transactionName, transDate, trans_value, userAccountInId, userAccountOutId, categoryId) " +
                        "values (%s, %s, %a, %d, %d, %d)",
                name, new Date(new java.util.Date().getTime()).toString(), transValue, null, accountOut, category);

        creationQuery(query);
    }

    public void createIncomeTransaction(String name, float transValue, long accountIn, long category)
            throws GenericTransactionException {
        String query = String.format("INSERT INTO Transaction " +
                        "(transactionName, transDate, trans_value, userAccountInId, userAccountOutId, categoryId) " +
                        "values (%s, %s, %a, %d, %d, %d)",
                name, new Date(new java.util.Date().getTime()).toString(), transValue, accountIn, null, category);

        creationQuery(query);
    }

    private void creationQuery(String query) throws GenericTransactionException {
        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var ok = stm.execute(query);

            stm.close();
            conn.close();

            if (!ok) throw new GenericTransactionException("Not created");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }

    public List<GenericTransaction> findAllIncomes() throws GenericTransactionException {
        String query = ("SELECT tr.id, tr.transValue, tr.transDate, tr.userAccountInId " +
                "FROM Transaction as tr where tr.userAccountOutId = NULL");
        List<GenericTransaction> trans = new ArrayList<>();

        try {
            var con = this.dbConfig.getConnection();
            var stm = con.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next()) trans.add(new GenericTransaction(rs.getLong("id"),
                    rs.getLong("userAccountInId"),
                    rs.getFloat("trans_value"), rs.getDate("transDate")));


            rs.close();
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return trans;
    }

    private long getMaxId() throws GenericTransactionException {
        String query = "SELECT MAX(id) FROM Transaction";
        long id;
        try {
            var con = this.dbConfig.getConnection();
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

}
