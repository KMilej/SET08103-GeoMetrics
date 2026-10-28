package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PopulationRepository {

    // 1. Fixed: Returns a List<City> to match the internal list creation and 'cities' ordering.
    // If you only want one city by name, you need a WHERE clause instead.
    public List<City> getCityPopulation(String cityName) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT ID, Name, Population FROM city ORDER BY Name WHERE Name = 'Edinbrugh'";

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                City city = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("Population")
                );
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch cities", e);
        }
        return cities;
    }

    // 2. Fixed: Corrected SQL aggregation syntax. Returns a single long total.
    public long getWorldPopulation() {
        String sql = "SELECT SUM(Population) AS TotalPopulation FROM country";

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getLong("TotalPopulation");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch world population", e);
        }
        return 0;
    }

    // 3. Fixed: Added WHERE clause to filter by region name.
    public long getRegionPopulation(String regionName) {
        String sql = "SELECT SUM(Population) AS TotalPopulation FROM country WHERE Region = ?";

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, regionName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("TotalPopulation");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch region population", e);
        }
        return 0;
    }

    // 4. Fixed: Added WHERE clause to filter by continent name.
    public long getContinentPopulation(String continentName) {
        String sql = "SELECT SUM(Population) AS TotalPopulation FROM country WHERE Continent = ?";

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, continentName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("TotalPopulation");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch continent population", e);
        }
        return 0;
    }

    // 5. Fixed: Queries the 'city' table instead of 'country' and filters by district name.
    public long getDistrictPopulation(String districtName) {
        String sql = "SELECT SUM(Population) AS TotalPopulation FROM city WHERE District = ?";

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, districtName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("TotalPopulation");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch district population", e);
        }
        return 0;
    }

}
