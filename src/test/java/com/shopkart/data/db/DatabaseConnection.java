package com.shopkart.data.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private DatabaseConnection() {
    }

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(
                    DatabaseConfig.getUrl(),
                    DatabaseConfig.getUsername(),
                    DatabaseConfig.getPassword());

        } catch (SQLException e) {

            throw new RuntimeException("Unable to connect to MySQL Database", e);

        }
    }

}