package com.napier.sem;

import com.napier.sem.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class App
{
    public static void main(String[] args)
    {
        // Load MySQL database driver
        try (Connection con = DatabaseConnection.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT Name, Population FROM country LIMIT 5")) {

            System.out.println("Successfully connected to MySQL!");

            while (rs.next()) {
                System.out.println(
                        rs.getString("Name") + " - "
                                + rs.getLong("Population")
                );
            }

        } catch (Exception e) {
            System.err.println("Application failed: " + e.getMessage());
            System.exit(1);
        }
    }
}