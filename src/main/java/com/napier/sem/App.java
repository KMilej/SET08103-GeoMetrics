package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    private Connection con;

    public static void main(String[] args) {
        App app = new App();
        int exitCode = 0;
        try {
            String location = args.length > 0 ? args[0] : "localhost:33060";
            int delay = args.length > 1 ? Integer.parseInt(args[1]) : 0;
            app.connect(location, delay);
            // Verify that the supplied world database has been imported.
            try (Statement statement = app.con.createStatement();
                 ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM country")) {
                result.next();
                System.out.println("Countries in world database: " + result.getInt(1));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Database connection interrupted");
            exitCode = 1;
        } catch (Exception e) {
            System.err.println("Application failed: " + e.getMessage());
            exitCode = 1;
        } finally {
            app.disconnect();
        }
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    /** Connect to world using the location and startup delay from Lab 07. */
    public void connect(String location, int delay)
            throws ClassNotFoundException, SQLException, InterruptedException {
        if (delay < 0) {
            throw new IllegalArgumentException("Database delay must not be negative");
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        int retries = 10;
        for (int i = 0; i < retries; i++) {
            System.out.println("Connecting to database...");
            Thread.sleep(delay);
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false"
                                + "&connectTimeout=5000&socketTimeout=10000",
                        "root", "example");
                System.out.println("Successfully connected");
                return;
            } catch (SQLException e) {
                System.err.println("Failed to connect to database attempt " + (i + 1));
                if (i == retries - 1) {
                    throw e;
                }
            }
        }
    }

    /** Close the database connection after the application finishes. */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            } finally {
                con = null;
            }
        }
    }
}
