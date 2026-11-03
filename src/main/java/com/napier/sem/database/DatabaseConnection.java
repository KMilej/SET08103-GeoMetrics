package com.napier.sem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() throws SQLException {

        String url = System.getenv("DB_URL");

        if (url == null || url.isBlank()) {
            url = "jdbc:mysql://localhost:3307/world"
                    + "?allowPublicKeyRetrieval=true&useSSL=false";
        }

        return DriverManager.getConnection(
                url,
                "root",
                "devpassword"
        );
    }
}