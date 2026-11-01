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

    /**
     *Calls the repository to fetch a specific city and wraps it in a single-item list
     *
     * @return a specific city's population
     * @throws SQLException if a database access error occurs
     */
    public List<City> getCityPopulation(String cityName) throws SQLException {
        List<City> cities = populationRepository.getCityPopulation(cityName);
        return cities; // Or return Collections.emptyList() if cities.isEmpty()
    }


     /**
     * Calls the repository to fetch for an aggregated population of a specific country
     *
      * @return a specific country's population
     */
    public long getCountryPopulation(String countryName){
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Name = ?";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, countryName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getLong("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch country population", e);
        }
        return 0;
    }

    /**
     * Calls the repository to fetch for an aggregated population of a specific district
     *
     * @return a specific district's population
     */
    public long getDistrictPopulation(String districtName) {
        String sql = "SELECT SUM(Population) AS Total FROM city WHERE District = ?";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, districtName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getLong("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch district population", e);
        }
        return 0;
    }

    /**
     * Calls the repository to fetch for an aggregated population of the whole world
     *
     * @return the world's population
     */
    public long getWorldPopulation() { // Changed int to long
        String sql = "SELECT SUM(Population) AS Total FROM country";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) return rs.getLong("Total");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch world population", e);
        }
        return 0;
    }

    /**
     * Calls the repository to fetch for an aggregated population of a specific continent
     *
     * @return a specific continent's population
     */
    public long getContinentPopulation(Continent continentName) {
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Continent = ?";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, continentName.name());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getLong("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch continent population", e);
        }
        return 0;
    }

    /**
     * Calls the repository to fetch for an aggregated population of a specific region
     *
     * @return a specific region's population
     */
    public long getRegionPopulation(String regionName) {
        String sql = "SELECT SUM(Population) AS Total FROM country WHERE Region = ?";
        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, regionName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getLong("Total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch region population", e);
        }
        return 0;
    }


}
