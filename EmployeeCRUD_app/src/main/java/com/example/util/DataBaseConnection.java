//package com.example.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import com.mysql.jdbc.Driver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class DataBaseConnection {
//    private static final String serviceLink="jdbc:mysql://127.0.0.1:3306/employeedb";
//    private static final String userName="root";
//    private static final String password="mysql@123";
//    private static final Logger log = LoggerFactory.getLogger(DataBaseConnection.class);
//    private static Connection connection;
//
//    private DataBaseConnection(){}
//    public static Connection connectToDatabase() throws SQLException {
//            DriverManager.registerDriver(new Driver());
//            connection = DriverManager.getConnection(serviceLink, userName, password);
//
//        return connection;
//    }
//}
package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String serviceLink = "jdbc:mysql://127.0.0.1:3306/employeedb";  // Corrected JDBC URL
    private static final String userName = "root";
    private static final String password = "mysql@123";
    private static Connection connection;

    public static Connection connectToDatabase() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(serviceLink, userName, password);
        }
        return connection;
    }
}
