package org.example.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection makeConnection() {
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "1234";
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/candidate", username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
