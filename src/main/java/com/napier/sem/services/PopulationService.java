package com.napier.sem.services;

import com.napier.sem.models.City;

import com.napier.sem.models.Continent;
import com.napier.sem.repositories.CityRepository;
import com.napier.sem.repositories.CountryRepository;
import com.napier.sem.repositories.PopulationRepository;
import com.napier.sem.database.DatabaseConnection;

import java.sql.*;
import java.util.Collections;
import java.util.List;


public class PopulationService {

    private final PopulationRepository populationRepository = new PopulationRepository();

    // 1. Calls your repository to fetch a specific city and wraps it in a single-item list
    public List<City> getCityPopulation(String cityName) throws SQLException {
        City city = (City) populationRepository.getCityPopulation(cityName);
        return city != null ? Collections.singletonList(city) : Collections.emptyList();
    }

    // 2. Returns the aggregated population of a specific country
    public int getCountryPopulation(String countryName) {
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Name = ?";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, countryName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch country population", e);
        }
        return 0;
    }

    // 3. Returns the aggregated population of a specific district from the city table
    public int getDistrictPopulation(String districtName) {
        String sql = "SELECT SUM(Population) AS Total FROM city WHERE District = ?";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, districtName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch district population", e);
        }
        return 0;
    }

    // 4. Returns the total population of the entire world
    public int getWorldPopulation() {
        String sql = "SELECT SUM(Population) AS Total FROM country";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) return rs.getInt("Total");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch world population", e);
        }
        return 0;
    }

    // 5. Uses the Continent Enum (converted to a String name) to query the continent population
    public int getContinentPopulation(Continent continentName) {
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Continent = ?";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            // Converts the enum to its string value (e.g., "Asia", "Europe")
            stmt.setString(1, continentName.name());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch continent population", e);
        }
        return 0;
    }

    // 6. Returns the aggregated population of a specific geographical region
    public int getRegionPopulation(String regionName) {
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Region = ?";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, regionName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch region population", e);
        }
        return 0;
    }

}
