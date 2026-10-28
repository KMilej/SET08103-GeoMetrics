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

}
