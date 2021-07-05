package com.income.management.repository;

import com.income.management.conf.DatabaseConfig;
import com.income.management.exception.GenericTransactionException;
import com.income.management.model.account.Account;
import com.income.management.model.account.AccountWithValue;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private final DatabaseConfig dbConfig;

    public AccountRepository(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public boolean createAccount(String name) throws GenericTransactionException {
        String query = String.format("INSERT INTO UserAccount (userAccountName) values (\"%s\")", name);
        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var ok = stm.execute(query);

            stm.close();
            conn.close();

            return ok;
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }

    public void changeAccountName(long id, String newName) throws GenericTransactionException {
        String query = String.format("UPDATE UserAccount SET userAccountName = %s WHERE id = %d", newName, id);

        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var ok = stm.execute(query);

            stm.close();
            conn.close();

            if (!ok) throw new GenericTransactionException("Not Updated");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }

    public List<AccountWithValue> findAccounts() throws GenericTransactionException {
        String query = "SELECT ab.ACCID, ab.ACCNAME, SUM(ab2.VALOR_ENTRADA) - SUM(ab.VALOR_SAIDA) AS TOTAL " +
                "FROM ACCOUNT_DESPESAS ab " +
                "left join ACCOUNT_ENTRADAS ab2 " +
                "on ab.ACCID = ab2.ACCID GROUP BY ab.ACCID ORDER BY ab.ACCNAME";

        List<AccountWithValue> accounts = new ArrayList<>();
        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var rs = stm.executeQuery(query);

            while (rs.next()) accounts.add(
                    new AccountWithValue(
                            rs.getLong("ACCID"),
                            rs.getString("ACCNAME"),
                            rs.getFloat("TOTAL")
                    )
            );

            rs.close();
            stm.close();
            conn.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return accounts;
    }

    public void deleteAccount(long id) throws GenericTransactionException {
        String query = String.format("DELETE FROM UserAccount AS uc WHERE uc.id = %d", id);

        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var rs = stm.execute(query);

            if (!rs) throw new GenericTransactionException("No deleted.");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }
}
