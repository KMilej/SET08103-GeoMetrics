package com.napier.sem.services;

import com.napier.sem.database.DatabaseConnection;

import java.sql.*;

/**
 */
public class PopulationService {


    public long getDistrictPopulation(String districtName) {

        String sql = "SELECT SUM(Population) AS Total FROM city WHERE District = ?";
        long population = 0;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, districtName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    population = rs.getLong("Total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch district population", e);
        }

        return printAggregatedReport("DISTRICT", districtName, population);
    }

    public long getWorldPopulation() {


        String sql = "SELECT SUM(Population) AS Total FROM country";
        long population = 0;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                population = rs.getLong("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch world population", e);
        }

        return printAggregatedReport("GLOBAL", "Whole World", population);
    }

    public long getContinentPopulation(String continentName) {

        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Continent = ?";
        long population = 0;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, continentName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    population = rs.getLong("Total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch continent population", e);
        }

        return printAggregatedReport("CONTINENT", continentName, population);

    }

    public long getRegionPopulation(String regionName) {
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Region = ?";
        long population = 0;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, regionName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    population = rs.getLong("Total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch region population", e);
        }

        return printAggregatedReport("REGION", regionName, population);
    }

    /**
     * Private helper method to handle a clean, reusable layout for aggregated single-row population metrics.
     */
    private long printAggregatedReport(String reportType, String locationName, long population) {
        System.out.println("\n========================================================");
        System.out.printf("               %s POPULATION REPORT                    \n", reportType);
        System.out.println("========================================================");
        System.out.printf("%-35s %-20s\n", "Location / Scope", "Total Population");
        System.out.println("--------------------------------------------------------");
        if (population == 0) {
            System.out.printf("%-35s %-20s\n", locationName, "No data available");
        } else {
            System.out.printf("%-35s %,20d\n", locationName, population);
        }
        return population;
    }

    public long getCountryPopulation(String countryName) {

        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Region = ?";
        long population = 0;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, countryName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    population = rs.getLong("Total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch country population", e);
        }

        return printAggregatedReport("Country", countryName, population);

    }


    public long getCityPopulation(String cityName) {

        String sql = "SELECT SUM(Population) AS Total FROM city WHERE Name = ?";
        long population = 0;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, cityName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    population = rs.getLong("Total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch city population", e);
        }

        return printAggregatedReport("CITY", cityName, population);

    }
}



