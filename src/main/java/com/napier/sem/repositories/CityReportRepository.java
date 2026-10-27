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
 * Provides database access for the ten UC02 city reports.
 * All results are ordered by population from largest to smallest.
 * Report formatting and CLI output belong outside this repository.
 *
 * <p>
 * These methods are implementation placeholders and currently throw
 * {@link UnsupportedOperationException} instead of querying the database.
 * </p>
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
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
    }

    /**
     * Retrieves all cities in the selected region.
     *
     * @param region the region name stored in country.Region
     * @return cities ordered by descending population; an empty list if none match
     * @throws SQLException if the database query fails
     */
    public List<City> getCitiesByRegionByPopulation(String region) throws SQLException {
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
    }

    /**
     * Retrieves all cities in the selected country.
     *
     * @param countryCode the three-letter country code, for example GBR
     * @return cities ordered by descending population; an empty list if none match
     * @throws SQLException if the database query fails
     */
    public List<City> getCitiesByCountryByPopulation(String countryCode) throws SQLException {
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
    }

    /**
     * Retrieves all cities in the selected district.
     *
     * @param district the district name stored in city.District
     * @return cities ordered by descending population; an empty list if none match
     * @throws SQLException if the database query fails
     */
    public List<City> getCitiesByDistrictByPopulation(String district) throws SQLException {
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
    }

    /**
     * Retrieves the most populated cities worldwide.
     *
     * @param topN the maximum number of cities to return; must be positive
     * @return at most topN cities ordered by descending population
     * @throws SQLException if the database query fails
     */
    public List<City> getTopNCitiesInWorld(int topN) throws SQLException {
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
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
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
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
        // TODO: Implement the database query and map its results to City objects.
        throw new UnsupportedOperationException("City report not implemented yet.");
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
}
