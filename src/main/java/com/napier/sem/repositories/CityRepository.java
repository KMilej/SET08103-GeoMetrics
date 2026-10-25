package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.City;
import com.napier.sem.models.Continent;

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

                    capitalCitiesByContinent.add(city);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch capital cities", e);
        }

        return capitalCitiesByContinent;
    }

    //All capital cities in a selected region.
    public List<City> getCapitalCitiesByRegion(String region) {
        List<City> capitalCitiesByRegion = new ArrayList<>();

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

                    capitalCitiesByRegion.add(city);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch capital cities", e);
        }

        return capitalCitiesByRegion;
    }
    //    4. Top N capital cities worldwide.

    public List<City> getTopNCapitalCities(int n) {
        List<City> topNCapitalCities = new ArrayList<>();

        String sql = """
                SELECT city.ID,
                       city.Name,
                       city.CountryCode,
                       city.District,
                       city.Population
                FROM city
                JOIN country ON city.ID = country.Capital
                ORDER BY city.Population DESC
                LIMIT ?
                """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setInt(1, n);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("CountryCode"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    );

                    topNCapitalCities.add(city);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch capital cities", e);
        }

        return topNCapitalCities;
    }

    //    5. Top N capital cities in a selected continent.
    public List<City> getTopNCapitalCitiesByContinent(int n, Continent continent) {
        List<City> topNCapitalCitiesByContinent = new ArrayList<>();

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
                LIMIT ?
                """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, continent.name().replace("_", " "));
            stmt.setInt(2, n);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("CountryCode"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    );

                    topNCapitalCitiesByContinent.add(city);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch capital cities", e);
        }

        return topNCapitalCitiesByContinent;
    }

    //    6. Top N capital cities in a selected region.
    public List<City> getTopNCapitalCitiesByRegion(int n, String region) {
        List<City> topNCapitalCitiesByRegion = new ArrayList<>();

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
                LIMIT ?
                """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, region);
            stmt.setInt(2, n);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("CountryCode"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    );

                    topNCapitalCitiesByRegion.add(city);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch capital cities", e);
        }

        return topNCapitalCitiesByRegion;
    }
}
