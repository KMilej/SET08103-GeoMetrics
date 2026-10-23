package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT * FROM city";

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
}
