package com.napier.sem.services;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.repositories.CityRepository;
import com.napier.sem.repositories.CountryRepository;
import com.napier.sem.repositories.PopReportRepository;
import com.napier.sem.repositories.PopulationRepository;

import com.napier.sem.database.DatabaseConnection;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class PopulationService {


    /**
     *
     * @throws SQLException if a database access error occurs
     */
        List<City> cities = populationRepository.getCityPopulation(cityName);

        System.out.println("\n========================================================================");
        System.out.println("                       CITY POPULATION REPORT                           ");
        System.out.println("========================================================================");
        System.out.printf("%-20s %-15s\n", "City Name", "Population");
        System.out.println("------------------------------------------------------------------------");

        if (cities == null || cities.isEmpty()) {
            System.out.println("No data found for city: " + cityName);
        } else {
            for (City city : cities) {
                System.out.printf("%-20s %,15d\n",
                        city.getName(),
                        city.getPopulation());
            }
        }

    }

    /**
     */
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Name = ?";
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

        printAggregatedReport("COUNTRY", countryName, population);
    }

    /**
     */
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

        printAggregatedReport("DISTRICT", districtName, population);
    }

    /**
     */
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

        printAggregatedReport("GLOBAL", "Whole World", population);
    }

    /**
     */
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Continent = ?";
        long population = 0;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, continentName.name());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    population = rs.getLong("Total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch continent population", e);
        }

        printAggregatedReport("CONTINENT", continentName.name(), population);
    }

    /**
     */
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

        printAggregatedReport("REGION", regionName, population);
    }

    /**
     * Private helper method to handle a clean, reusable layout for aggregated single-row population metrics.
     */
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
    }
}


