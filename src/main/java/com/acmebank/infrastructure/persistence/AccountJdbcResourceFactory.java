package com.acmebank.infrastructure.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

public class AccountJdbcResourceFactory {

    @Resource(name = "java:global/jdbc/AcmeBankDB")
    private DataSource dataSource;

    @Produces
    @AccountJdbcResource
    @RequestScoped // This would be transaction scoped in Java EE 7
    public Connection createAccountConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeAccountConnection(
            @Disposes @AccountJdbcResource Connection connection)
            throws SQLException {
        connection.close();
    }

    @Produces
    @AccountJdbcResource
    @RequestScoped // This would be transaction scoped in Java EE 7
    public Statement createAccountStatement(
            @AccountJdbcResource Connection connection) throws SQLException {
        return connection.createStatement();
    }

    public void closeAccountStatement(
            @Disposes @AccountJdbcResource Statement statement)
            throws SQLException {
        statement.close();
    }
}
