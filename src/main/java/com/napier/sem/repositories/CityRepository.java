package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.City;
import com.napier.sem.models.Continent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CityRepository {

    /**
     * Retrieves all cities from the database,
     * ordered by population from largest to smallest.
     *
     * @return a list of all cities sorted by population
     * @throws RuntimeException if a database access error occurs
     */

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT * FROM city ORDER BY Population DESC";

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                City city = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );

                cities.add(city);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch cities", e);
        }

        return cities;
    }

    //getCitiesByContinent
    /**
     * Retrieves all cities in a specified continent from the database,
     * ordered by population from largest to smallest.
     *
     * @param continent the continent to retrieve cities from
     * @return a list of cities in the specified continent
     * @throws IllegalArgumentException if continent is null
     * @throws RuntimeException if a database access error occurs
     */

    public List<City> getCitiesByContinent(Continent continent) {

        if (continent == null) {
            throw new IllegalArgumentException("Continent cannot be null");
        }
        List<City> citiesByContinent = new ArrayList<>();

        String sql = """
                SELECT city.ID,
                       city.Name,
                       city.CountryCode,
                       city.District,
                       city.Population
                FROM city
                JOIN country ON city.CountryCode = country.Code
                WHERE country.Continent = ?
                ORDER BY city.Population DESC
                """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, continent.name().replace("_", " "));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("CountryCode"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    );

                    citiesByContinent.add(city);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch cities", e);
        }

        return citiesByContinent;
    }

    // 1. Get capital cities

    /**
     /**
     * Retrieves capital cities worldwide from the database,
     * ordered by population from largest to smallest.
     *
     * @param topN the maximum number of capital cities to retrieve;
     *             0 or a negative value returns all capital cities
     * @return a list of capital cities sorted by population
     * @throws SQLException if a database access error occurs
     */

    public List<City> getCapitalCities(int topN) throws SQLException {
        List<City> capitalCities = new ArrayList<>();

        String sql = """
                SELECT city.ID,
                       city.Name,
                       city.CountryCode,
                       city.District,
                       city.Population
                FROM city
                JOIN country ON city.ID = country.Capital
                ORDER BY city.Population DESC
                """ + (topN > 0 ? " LIMIT ?" : "");

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            if (topN > 0) {
                stmt.setInt(1, topN);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("CountryCode"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    );

                    capitalCities.add(city);
                }
            }
        }

        return capitalCities;
    }

    // 2. Get capital cities by continent

    /**
     * Retrieves capital cities in a specified continent from the database,
     * ordered by population from largest to smallest.
     *
     * @param continent the continent to retrieve capital cities from
     * @param topN the maximum number of capital cities to retrieve;
     *             0 or a negative value returns all capital cities
     * @return a list of capital cities in the specified continent
     * @throws NullPointerException if continent is null
     * @throws SQLException if a database access error occurs
     */

    public List<City> getCapitalCitiesByContinent(Continent continent, int topN)
            throws SQLException {

        List<City> capitalCitiesByContinent = new ArrayList<>();

        String sql = """
        SELECT city.*
        FROM city
        JOIN country ON city.ID = country.Capital
        WHERE country.Continent = ?
        ORDER BY city.Population DESC
        """ + (topN > 0 ? " LIMIT ?" : "");

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, continent.name().replace("_", " "));
            if (topN > 0) {
                stmt.setInt(2, topN);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("CountryCode"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    );

                    capitalCitiesByContinent.add(city);
                }
            }
        }

        return capitalCitiesByContinent;
    }

    // 3. Get capital cities by region
    /**
     * Retrieves capital cities in a specified region from the database,
     * ordered by population from largest to smallest.
     *
     * @param region the region to retrieve capital cities from
     * @param topN the maximum number of capital cities to retrieve;
     *             0 or a negative value returns all capital cities
     * @return a list of capital cities in the specified region
     * @throws SQLException if a database access error occurs
     */

    public List<City> getCapitalCitiesByRegion(String region, int topN)
            throws SQLException {

        List<City> capitalCitiesByRegion = new ArrayList<>();

        String sql = """
        SELECT city.*
        FROM city
        JOIN country ON city.ID = country.Capital
        WHERE country.Region = ?
        ORDER BY city.Population DESC
        """ + (topN > 0 ? " LIMIT ?" : "");

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, region);
            if (topN > 0) {
                stmt.setInt(2, topN);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("CountryCode"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    );

                    capitalCitiesByRegion.add(city);
                }
            }
        }

        return capitalCitiesByRegion;
    }

    public List<City> getCityPopulation() {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT ID, Name, Population FROM city ORDER BY Name";

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
}
