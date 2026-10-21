package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        // Load MySQL database driver
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load MySQL driver");
            System.exit(-1);
        }

        // Connection details for IntelliJ and Docker
        String url = System.getenv("DB_URL");

        if (url == null || url.isBlank())
        {
            url = "jdbc:mysql://localhost:3307/world"
                    + "?allowPublicKeyRetrieval=true&useSSL=false";
        }

        String username = "root";
        String password = "devpassword";

        Connection con = null;
        int retries = 10;

        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database... Attempt " + (i + 1));

            try
            {
                con = DriverManager.getConnection(url, username, password);

                System.out.println("Successfully connected to MySQL!");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect: " + sqle.getMessage());

                // Wait 2 seconds before trying again
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException ie)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        if (con != null)
        {
            try
            {
                // Test that the world database contains data
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                        "SELECT Name FROM country LIMIT 5"
                );

                System.out.println("Countries in database:");

                while (rs.next())
                {
                    System.out.println(rs.getString("Name"));
                }

                rs.close();
                stmt.close();

                // Close database connection
                con.close();
                System.out.println("Database connection closed.");
            }
            catch (SQLException e)
            {
                System.out.println("Database query failed: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("Could not connect to MySQL after " + retries + " attempts.");
        }
    }
}