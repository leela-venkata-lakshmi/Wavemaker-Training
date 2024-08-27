package com.wavemaker.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String serviceLink = "jdbc:mysql://127.0.0.1:3306/TODO?useSSL=false";
    private static final String userName = "root";
    private static final String password = "mysql@123";
    private static final Logger log = LoggerFactory.getLogger(DatabaseConnection.class);
    private static Connection connection;

    public static Connection connectToDatabase() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found", e);
        }
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(serviceLink, userName, password);
        }
        return connection;
    }

}
