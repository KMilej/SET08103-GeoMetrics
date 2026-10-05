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
                app.showMenu(scanner);
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

    /** Display six categories and the reports belonging to the selected category. */
    public void showMenu(Scanner scanner) {
        String[] categories = {
                "UC01 - Country Reports",
                "UC02 - City Reports",
                "UC03 - Capital City Reports",
                "UC04 - Population Distribution Reports",
                "UC05 - Population Information",
                "UC06 - Language Population Report"
        };

        String[] reports = {
                "All countries in the world",
                "Countries in a continent",
                "Countries in a region",
                "Top N countries in the world",
                "Top N countries in a continent",
                "Top N countries in a region",
                "All cities in the world",
                "Cities in a continent",
                "Cities in a region",
                "Cities in a country",
                "Cities in a district",
                "Top N cities in the world",
                "Top N cities in a continent",
                "Top N cities in a region",
                "Top N cities in a country",
                "Top N cities in a district",
                "All capital cities in the world",
                "Capital cities in a continent",
                "Capital cities in a region",
                "Top N capital cities in the world",
                "Top N capital cities in a continent",
                "Top N capital cities in a region",
                "Population distribution by continent",
                "Population distribution by region",
                "Population distribution by country",
                "World population",
                "Continent population",
                "Region population",
                "Country population",
                "District population",
                "City population",
                "Major language population report"
        };

        while (true) {
            System.out.println("\nGeoMetrics reports");
            for (int i = 0; i < categories.length; i++) {
                System.out.println((i + 1) + ". " + categories[i]);
            }
            System.out.println("0. Exit");

            int category = readChoice(scanner, 1, 6);
            if (category == 0 || category == -1) {
                return;
            }

            int first;
            int last;
            switch (category) {
                case 1:
                    first = 1;
                    last = 6;
                    break;
                case 2:
                    first = 7;
                    last = 16;
                    break;
                case 3:
                    first = 17;
                    last = 22;
                    break;
                case 4:
                    first = 23;
                    last = 25;
                    break;
                case 5:
                    first = 26;
                    last = 31;
                    break;
                case 6:
                    first = 32;
                    last = 32;
                    break;
                default:
                    continue;
            }

            while (true) {
                System.out.println("\n" + categories[category - 1]);
                for (int report = first; report <= last; report++) {
                    System.out.println(report + ". " + reports[report - 1]);
                }
                System.out.println("0. Back");

                int report = readChoice(scanner, first, last);
                if (report == -1) {
                    return;
                }
                if (report == 0) {
                    break;
                }

                executeReport(report, scanner);
            }
        }
    }

    /** Read a valid choice; 0 means back/exit, and -1 means the input has ended. */
    private int readChoice(Scanner scanner, int first, int last) {
        while (true) {
            System.out.print("Choose an option: ");
            if (!scanner.hasNextLine()) {
                return -1;
            }

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 0 || (choice >= first && choice <= last)) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                // Ask again when the input is not a number.
            }

            System.out.println("Enter 0 or a number between " + first + " and " + last);
        }
    }

    /** Add each report's input prompts and database query to its numbered case. */
    private void executeReport(int report, Scanner scanner) {
        switch (report) {
            case 1: {
                // TODO: All countries in the world.
                System.out.println("All countries in the world: not implemented yet");
                break;
            }
            case 2: {
                // TODO: Countries in a continent.
                System.out.println("Countries in a continent: not implemented yet");
                break;
            }
            case 3: {
                // TODO: Countries in a region.
                System.out.println("Countries in a region: not implemented yet");
                break;
            }
            case 4: {
                // TODO: Top N countries in the world.
                System.out.println("Top N countries in the world: not implemented yet");
                break;
            }
            case 5: {
                // TODO: Top N countries in a continent.
                System.out.println("Top N countries in a continent: not implemented yet");
                break;
            }
            case 6: {
                // TODO: Top N countries in a region.
                System.out.println("Top N countries in a region: not implemented yet");
                break;
            }
            case 7: {
                // TODO: All cities in the world.
                System.out.println("All cities in the world: not implemented yet");
                break;
            }
            case 8: {
                // TODO: Cities in a continent.
                System.out.println("Cities in a continent: not implemented yet");
                break;
            }
            case 9: {
                // TODO: Cities in a region.
                System.out.println("Cities in a region: not implemented yet");
                break;
            }
            case 10: {
                // TODO: Cities in a country.
                System.out.println("Cities in a country: not implemented yet");
                break;
            }
            case 11: {
                // TODO: Cities in a district.
                System.out.println("Cities in a district: not implemented yet");
                break;
            }
            case 12: {
                // TODO: Top N cities in the world.
                System.out.println("Top N cities in the world: not implemented yet");
                break;
            }
            case 13: {
                // TODO: Top N cities in a continent.
                System.out.println("Top N cities in a continent: not implemented yet");
                break;
            }
            case 14: {
                // TODO: Top N cities in a region.
                System.out.println("Top N cities in a region: not implemented yet");
                break;
            }
            case 15: {
                // TODO: Top N cities in a country.
                System.out.println("Top N cities in a country: not implemented yet");
                break;
            }
            case 16: {
                // TODO: Top N cities in a district.
                System.out.println("Top N cities in a district: not implemented yet");
                break;
            }
            case 17: {
                // TODO: All capital cities in the world.
                System.out.println("All capital cities in the world: not implemented yet");
                break;
            }
            case 18: {
                // TODO: Capital cities in a continent.
                System.out.println("Capital cities in a continent: not implemented yet");
                break;
            }
            case 19: {
                // TODO: Capital cities in a region.
                System.out.println("Capital cities in a region: not implemented yet");
                break;
            }
            case 20: {
                // TODO: Top N capital cities in the world.
                System.out.println("Top N capital cities in the world: not implemented yet");
                break;
            }
            case 21: {
                // TODO: Top N capital cities in a continent.
                System.out.println("Top N capital cities in a continent: not implemented yet");
                break;
            }
            case 22: {
                // TODO: Top N capital cities in a region.
                System.out.println("Top N capital cities in a region: not implemented yet");
                break;
            }
            case 23: {
                // TODO: Population distribution by continent.
                System.out.println("Population distribution by continent: not implemented yet");
                break;
            }
            case 24: {
                // TODO: Population distribution by region.
                System.out.println("Population distribution by region: not implemented yet");
                break;
            }
            case 25: {
                // TODO: Population distribution by country.
                System.out.println("Population distribution by country: not implemented yet");
                break;
            }
            case 26: {
                // TODO: World population.
                System.out.println("World population: not implemented yet");
                break;
            }
            case 27: {
                // TODO: Continent population.
                System.out.println("Continent population: not implemented yet");
                break;
            }
            case 28: {
                // TODO: Region population.
                System.out.println("Region population: not implemented yet");
                break;
            }
            case 29: {
                // TODO: Country population.
                System.out.println("Country population: not implemented yet");
                break;
            }
            case 30: {
                // TODO: District population.
                System.out.println("District population: not implemented yet");
                break;
            }
            case 31: {
                // TODO: City population.
                System.out.println("City population: not implemented yet");
                break;
            }
            case 32: {
                // TODO: Major language population report.
                System.out.println("Major language population report: not implemented yet");
                break;
            }
            default:
                System.out.println("Unknown report");
                break;
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
