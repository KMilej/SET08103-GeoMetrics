package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    private Connection con;

    public static void main(String[] args) {
        App app = new App();
        int exitCode = 0;
        try {
            String location = args.length > 0 ? args[0] : "localhost:33060";
            int delay = args.length > 1 ? Integer.parseInt(args[1]) : 0;
            app.connect(location, delay);
            // GitHub Actions checks the database without waiting for input.
            if (args.length > 2 && "check".equals(args[2])) {
                try (Statement statement = app.con.createStatement();
                     ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM country")) {
                    result.next();
                    System.out.println("Countries in world database: " + result.getInt(1));
                }
                return;
            }

            try (Scanner scanner = new Scanner(System.in)) {
                boolean running = true;
                while (running) {
                    System.out.println("\nGeoMetrics reports");
                    System.out.println("1. All countries in the world");
                    System.out.println("2. Countries in a continent");
                    System.out.println("3. Countries in a region");
                    System.out.println("4. Top N countries in the world");
                    System.out.println("5. Top N countries in a continent");
                    System.out.println("6. Top N countries in a region");
                    System.out.println("7. All cities in the world");
                    System.out.println("8. Cities in a continent");
                    System.out.println("9. Cities in a region");
                    System.out.println("10. Cities in a country");
                    System.out.println("11. Cities in a district");
                    System.out.println("12. Top N cities in the world");
                    System.out.println("13. Top N cities in a continent");
                    System.out.println("14. Top N cities in a region");
                    System.out.println("15. Top N cities in a country");
                    System.out.println("16. Top N cities in a district");
                    System.out.println("17. All capital cities in the world");
                    System.out.println("18. Capital cities in a continent");
                    System.out.println("19. Capital cities in a region");
                    System.out.println("20. Top N capital cities in the world");
                    System.out.println("21. Top N capital cities in a continent");
                    System.out.println("22. Top N capital cities in a region");
                    System.out.println("23. Population distribution by continent");
                    System.out.println("24. Population distribution by region");
                    System.out.println("25. Population distribution by country");
                    System.out.println("26. World population");
                    System.out.println("27. Continent population");
                    System.out.println("28. Region population");
                    System.out.println("29. Country population");
                    System.out.println("30. District population");
                    System.out.println("31. City population");
                    System.out.println("32. Major language population report");
                    System.out.println("0. Exit");
                    System.out.print("Choose a report (0-32): ");
                    if (!scanner.hasNextLine()) {
                        break;
                    }
                    int report;
                    try {
                        report = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number between 0 and 32");
                        continue;
                    }
                    switch (report) {
                        case 0:
                            running = false;
                            break;
                        case 1:
                            // TODO: All countries in the world.
                            System.out.println("All countries in the world: not implemented yet");
                            break;
                        case 2:
                            // TODO: Countries in a continent.
                            System.out.println("Countries in a continent: not implemented yet");
                            break;
                        case 3:
                            // TODO: Countries in a region.
                            System.out.println("Countries in a region: not implemented yet");
                            break;
                        case 4:
                            // TODO: Top N countries in the world.
                            System.out.println("Top N countries in the world: not implemented yet");
                            break;
                        case 5:
                            // TODO: Top N countries in a continent.
                            System.out.println("Top N countries in a continent: not implemented yet");
                            break;
                        case 6:
                            // TODO: Top N countries in a region.
                            System.out.println("Top N countries in a region: not implemented yet");
                            break;
                        case 7:
                            // TODO: All cities in the world.
                            System.out.println("All cities in the world: not implemented yet");
                            break;
                        case 8:
                            // TODO: Cities in a continent.
                            System.out.println("Cities in a continent: not implemented yet");
                            break;
                        case 9:
                            // TODO: Cities in a region.
                            System.out.println("Cities in a region: not implemented yet");
                            break;
                        case 10:
                            // TODO: Cities in a country.
                            System.out.println("Cities in a country: not implemented yet");
                            break;
                        case 11:
                            // TODO: Cities in a district.
                            System.out.println("Cities in a district: not implemented yet");
                            break;
                        case 12:
                            // TODO: Top N cities in the world.
                            System.out.println("Top N cities in the world: not implemented yet");
                            break;
                        case 13:
                            // TODO: Top N cities in a continent.
                            System.out.println("Top N cities in a continent: not implemented yet");
                            break;
                        case 14:
                            // TODO: Top N cities in a region.
                            System.out.println("Top N cities in a region: not implemented yet");
                            break;
                        case 15:
                            // TODO: Top N cities in a country.
                            System.out.println("Top N cities in a country: not implemented yet");
                            break;
                        case 16:
                            // TODO: Top N cities in a district.
                            System.out.println("Top N cities in a district: not implemented yet");
                            break;
                        case 17:
                            // TODO: All capital cities in the world.
                            System.out.println("All capital cities in the world: not implemented yet");
                            break;
                        case 18:
                            // TODO: Capital cities in a continent.
                            System.out.println("Capital cities in a continent: not implemented yet");
                            break;
                        case 19:
                            // TODO: Capital cities in a region.
                            System.out.println("Capital cities in a region: not implemented yet");
                            break;
                        case 20:
                            // TODO: Top N capital cities in the world.
                            System.out.println("Top N capital cities in the world: not implemented yet");
                            break;
                        case 21:
                            // TODO: Top N capital cities in a continent.
                            System.out.println("Top N capital cities in a continent: not implemented yet");
                            break;
                        case 22:
                            // TODO: Top N capital cities in a region.
                            System.out.println("Top N capital cities in a region: not implemented yet");
                            break;
                        case 23:
                            // TODO: Population distribution by continent.
                            System.out.println("Population distribution by continent: not implemented yet");
                            break;
                        case 24:
                            // TODO: Population distribution by region.
                            System.out.println("Population distribution by region: not implemented yet");
                            break;
                        case 25:
                            // TODO: Population distribution by country.
                            System.out.println("Population distribution by country: not implemented yet");
                            break;
                        case 26:
                            // TODO: World population.
                            System.out.println("World population: not implemented yet");
                            break;
                        case 27:
                            // TODO: Continent population.
                            System.out.println("Continent population: not implemented yet");
                            break;
                        case 28:
                            // TODO: Region population.
                            System.out.println("Region population: not implemented yet");
                            break;
                        case 29:
                            // TODO: Country population.
                            System.out.println("Country population: not implemented yet");
                            break;
                        case 30:
                            // TODO: District population.
                            System.out.println("District population: not implemented yet");
                            break;
                        case 31:
                            // TODO: City population.
                            System.out.println("City population: not implemented yet");
                            break;
                        case 32:
                            // TODO: Major language population report.
                            System.out.println("Major language population report: not implemented yet");
                            break;
                        default:
                            System.out.println("Please enter a number between 0 and 32");
                            break;
                    }
                }
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
