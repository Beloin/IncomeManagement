package com.income.management.repository;

import com.income.management.conf.DatabaseConfig;
import com.income.management.exception.GenericTransactionException;
import com.income.management.model.account.Account;
import com.income.management.model.account.AccountWithValue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    private final DatabaseConfig dbConfig;

    public AccountRepository(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public void createAccount(String name) throws GenericTransactionException {
        String query = String.format("INSERT INTO UserAccount (userAccountName) values (\"%s\")", name);
        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var ok = stm.execute(query);

            stm.close();
            if (ok) throw new GenericTransactionException("Not created");
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
            if (ok) throw new GenericTransactionException("Not Updated");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }

    public List<AccountWithValue> findAccounts() throws GenericTransactionException {
        String totalQuery = "SELECT q1.ACCID, q1.ACCNAME, (RECEITAS - DESPESAS) AS TOTAL" +
                " FROM (SELECT ad1.ACCNAME, ad1.ACCID, COALESCE(sum(ad1.VALOR_ENTRADA), 0) AS RECEITAS FROM ACCOUNT_ENTRADAS AS ad1 group by ACCID) as q1 LEFT JOIN " +
                "(SELECT ad.ACCNAME, ad.ACCID, COALESCE(sum(ad.VALOR_SAIDA), 0) AS DESPESAS FROM ACCOUNT_DESPESAS AS ad group by ACCID) as q2 ON q1.ACCID = q2.ACCID;";

        List<AccountWithValue> accounts = new ArrayList<>();
        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            stm.execute("CREATE OR REPLACE VIEW ACCOUNT_DESPESAS as " +
                    "SELECT ua.id as ACCID," +
                    " ua.userAccountName AS ACCNAME," +
                    " tr.transValue      AS VALOR_SAIDA " +
                    "FROM UserAccount AS ua" +
                    "         LEFT JOIN Transaction tr" +
                    "                   on tr.userAccountOutId = ua.id;");
            var stm3 = conn.createStatement();
            String query2 = " CREATE OR REPLACE VIEW ACCOUNT_ENTRADAS as " +
                    " SELECT ua.id              as ACCID, " +
                    "       ua.userAccountName AS ACCNAME," +
                    "       tr.transValue      AS VALOR_ENTRADA " +
                    "FROM UserAccount AS ua" +
                    "         LEFT JOIN Transaction tr" +
                    "                   on tr.userAccountInId = ua.id";
            stm3.execute(query2);
            stm.close();
            stm3.close();

            var stm2 = conn.createStatement();
            var rs = stm2.executeQuery(totalQuery);

            while (rs.next()) {
                accounts.add(
                        new AccountWithValue(
                                rs.getLong("ACCID"),
                                rs.getString("ACCNAME"),
                                rs.getFloat("TOTAL")
                        )
                );
            }

            rs.close();
            stm2.close();
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }

        return accounts;
    }

    public void deleteAccount(long id) throws GenericTransactionException {
        String query = String.format("DELETE FROM UserAccount WHERE id = %d", id);

        try {
            var conn = this.dbConfig.getConnection();
            var stm = conn.createStatement();
            var rs = stm.execute(query);

            if (rs) throw new GenericTransactionException("No deleted.");
        } catch (Exception e) {
            throw new GenericTransactionException(e.getMessage(), e);
        }
    }
}
