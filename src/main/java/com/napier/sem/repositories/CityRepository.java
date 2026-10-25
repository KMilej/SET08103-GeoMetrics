package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    public List<City> getAllCities() throws SQLException {
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

    //getCitiesByContinent(String continent)

    public List<City> getCitiesByContinent(String continent) {
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

            stmt.setString(1, continent);

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

    //        1. All capital cities worldwide.

    public List<City> getAllCapitalCities() {
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
                """;

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

                capitalCities.add(city);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch capital cities", e);
        }

        return capitalCities;
    }

    //  2. All capital cities in a selected continent.
    public List<City> getCapitalCitiesByContinent(String continent) {
        List<City> capitalCitiesByContinent = new ArrayList<>();

        String sql = """    
                SELECT city.ID,
                   city.Name,
                   city.CountryCode,
                   city.District,
                   city.Population
                FROM city
                JOIN country ON city.ID = country.Capital
                WHERE country.Continent = ?
                ORDER BY city.Population DESC
                """;

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

                capitalCitiesByContinent.add(city);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch capital cities", e);
        }

        return capitalCitiesByContinent;
    }
    public List<City> getCapitalCitiesInRegion(String region) {
        List<City> capitalCitiesInRegion = new ArrayList<>();

        String sql = """
            SELECT city.ID,
                   city.Name,
                   city.CountryCode,
                   city.District,
                   city.Population
            FROM city
            JOIN country ON city.ID = country.Capital
            WHERE country.Region = ?
            ORDER BY city.Population DESC
            """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, region);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("CountryCode"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    );

                    capitalCitiesInRegion.add(city);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch capital cities", e);
        }

        return capitalCitiesInRegion;
    }
    //    4. Top N capital cities worldwide.
    //    5. Top N capital cities in a selected continent.
    //    6. Top N capital cities in a selected region.

    }
