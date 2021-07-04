package com.income.management.conf;

import com.income.management.exception.SQLConnectionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {
    /**
     * Get SQL Connection.
     *
     * @return Connection
     * @throws SQLConnectionException ex
     */
    public Connection getConnection() throws SQLConnectionException {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/IncomeManagement?"
                    + "user=root&password=password");
        } catch (SQLException throwable) {
            throw new SQLConnectionException(throwable.getMessage(), throwable);
        }

        return con;
    }

}
