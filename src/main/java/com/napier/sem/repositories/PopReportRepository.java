package com.napier.sem.repositories;


import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PopReportRepository
{

    /**
     *  Retrieves a city from the database
     *  to match the internal list creation and 'cities' ordering.
     *
     * @return a cities population
     * @throws RuntimeException if a database access error occurs
     */
    public List<City> getCityPopulation(String cityName) {
        List<City> cities = new ArrayList<>();
        // Fixed: Moved WHERE before ORDER BY
        String sql = "SELECT ID, Name, Population FROM city WHERE Name = ? ORDER BY Name";

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) { // Removed executeQuery from try-with-resources

            // Fixed: Set the cityName parameter into the statement
            stmt.setString(1, cityName);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getInt("Population")
                    );
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch cities", e);
        }
        return cities;
    }


    /**
     *  Retrieves the population of the world from the database
     *  sum will add all the population data together
     *
     * @return the worlds population
     * @throws RuntimeException if a database access error occurs
     */
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

    /**
     *  Retrieves a region from the database
     *  to match the internal list creation and 'region' ordering.
     *
     * @return a region's population
     * @throws RuntimeException if a database access error occurs
     */
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

    /**
     *  Retrieves a continent from the database
     *  to match the internal list creation and 'continent' ordering.
     *
     * @return a continent population
     * @throws RuntimeException if a database access error occurs
     */
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

    /**
     *  Retrieves a district from the database
     *  to match the internal list creation and 'district' ordering.
     *
     * @return a district's population
     * @throws RuntimeException if a database access error occurs
     */
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

