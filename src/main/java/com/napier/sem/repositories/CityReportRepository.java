/****************************************************************
 * Project: GeoMetrics
 * File:    CityReportRepository.java
 * Author:  Kamil Milej
 ***************************************************************/

package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.City;
import com.napier.sem.models.Continent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads city data for UC02 reports.
 * Cities are returned from largest to smallest population.
 */
public class CityReportRepository {

    /**
     * Retrieves all cities worldwide.
     *
     * @return cities ordered by descending population; an empty list if none match
     * @throws SQLException if the database query fails
     */
    public List<City> getAllCitiesByPopulation() throws SQLException {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT ID, Name, CountryCode, District, Population FROM city ORDER BY Population DESC";

        try (Connection connection = DatabaseConnection.connect();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                cities.add(new City(
                        result.getInt("ID"),
                        result.getString("Name"),
                        result.getString("CountryCode"),
                        result.getString("District"),
                        result.getInt("Population")));
            }

        }
        return cities;
    }

    /**
     * Retrieves all cities in the selected continent.
     *
     * @param continent the selected continent
     * @return cities ordered by descending population; an empty list if none match
     * @throws SQLException if the database query fails
     */
    public List<City> getCitiesByContinentByPopulation(Continent continent) throws SQLException {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population"
                + " FROM city"
                + " JOIN country ON city.CountryCode = country.Code"
                + " WHERE country.Continent = ?"
                + " ORDER BY city.Population DESC";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, continent.name().replace("_", " "));

            try (ResultSet result = statement.executeQuery()) {
                return readCities(result);
            }
        }
    }

    /**
     * Retrieves all cities in the selected region.
     *
     * @param region the region name stored in country.Region
     * @return cities ordered by descending population; an empty list if none match
     * @throws SQLException if the database query fails
     */
    public List<City> getCitiesByRegionByPopulation(String region) throws SQLException {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population"
                + " FROM city"
                + " JOIN country ON city.CountryCode = country.Code"
                + " WHERE country.Region = ?"
                + " ORDER BY city.Population DESC";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, region);

            try (ResultSet result = statement.executeQuery()) {
                return readCities(result);
            }
        }
    }

    /**
     * Retrieves all cities in the selected country.
     *
     * @param countryCode the three-letter country code, for example GBR
     * @return cities ordered by descending population; an empty list if none match
     * @throws SQLException if the database query fails
     */
    public List<City> getCitiesByCountryByPopulation(String countryCode) throws SQLException {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population"
                + " FROM city"
                + " WHERE city.CountryCode = ?"
                + " ORDER BY city.Population DESC";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, countryCode);

            try (ResultSet result = statement.executeQuery()) {
                return readCities(result);
            }
        }
    }

    /**
     * Retrieves all cities in the selected district.
     *
     * @param district the district name stored in city.District
     * @return cities ordered by descending population; an empty list if none match
     * @throws SQLException if the database query fails
     */
    public List<City> getCitiesByDistrictByPopulation(String district) throws SQLException {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population"
                + " FROM city"
                + " WHERE city.District = ?"
                + " ORDER BY city.Population DESC";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, district);

            try (ResultSet result = statement.executeQuery()) {
                return readCities(result);
            }
        }
    }

    /**
     * Retrieves the most populated cities worldwide.
     *
     * @param topN the maximum number of cities to return; must be positive
     * @return at most topN cities ordered by descending population
     * @throws SQLException if the database query fails
     */
    public List<City> getTopNCitiesInWorld(int topN) throws SQLException {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population"
                + " FROM city"
                + " ORDER BY city.Population DESC"
                + " LIMIT ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, topN);

            try (ResultSet result = statement.executeQuery()) {
                return readCities(result);
            }
        }
    }

    /**
     * Retrieves the most populated cities in the selected continent.
     *
     * @param topN      the maximum number of cities to return; must be positive
     * @param continent the selected continent
     * @return at most topN cities ordered by descending population
     * @throws SQLException if the database query fails
     */
    public List<City> getTopNCitiesByContinent(int topN, Continent continent) throws SQLException {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population"
                + " FROM city"
                + " JOIN country ON city.CountryCode = country.Code"
                + " WHERE country.Continent = ?"
                + " ORDER BY city.Population DESC"
                + " LIMIT ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, continent.name().replace("_", " "));
            statement.setInt(2, topN);

            try (ResultSet result = statement.executeQuery()) {
                return readCities(result);
            }
        }
    }

    /**
     * Retrieves the most populated cities in the selected region.
     *
     * @param topN   the maximum number of cities to return; must be positive
     * @param region the region name stored in country.Region
     * @return at most topN cities ordered by descending population
     * @throws SQLException if the database query fails
     */
    public List<City> getTopNCitiesByRegion(int topN, String region) throws SQLException {
        String sql = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population"
                + " FROM city"
                + " JOIN country ON city.CountryCode = country.Code"
                + " WHERE country.Region = ?"
                + " ORDER BY city.Population DESC"
                + " LIMIT ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, region);
            statement.setInt(2, topN);

            try (ResultSet result = statement.executeQuery()) {
                return readCities(result);
            }
        }
    }

    /**
     * Retrieves the most populated cities in the selected country.
     *
     * @param topN        the maximum number of cities to return; must be positive
     * @param countryCode the three-letter country code, for example GBR
     * @return at most topN cities ordered by descending population
     * @throws SQLException if the database query fails
     */
    public List<City> getTopNCitiesByCountry(int topN, String countryCode) throws SQLException {
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
    }

    /**
     * Retrieves the most populated cities in the selected district.
     *
     * @param topN     the maximum number of cities to return; must be positive
     * @param district the district name stored in city.District
     * @return at most topN cities ordered by descending population
     * @throws SQLException if the database query fails
     */
    public List<City> getTopNCitiesByDistrict(int topN, String district) throws SQLException {
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
    }

    /**
     * Creates a list of cities from database results.
     *
     * @param result the rows returned by the query
     * @return the cities read from the results
     * @throws SQLException if reading the results fails
     */
    private List<City> readCities(ResultSet result) throws SQLException {
        List<City> cities = new ArrayList<>();
        while (result.next()) {
            cities.add(new City(
                    result.getInt("ID"),
                    result.getString("Name"),
                    result.getString("CountryCode"),
                    result.getString("District"),
                    result.getInt("Population")));
        }
        return cities;
    }
}
