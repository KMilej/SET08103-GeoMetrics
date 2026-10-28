package com.napier.sem.services;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.repositories.CapitalCityRepository;
import com.napier.sem.repositories.CityRepository;

import java.sql.SQLException;
import java.util.List;

public class CapitalCityService {

    // Repository fields
    private final CapitalCityRepository capitalCityRepository;
    private final CityRepository cityRepository;

    // Constructor for Mockito testing
    public CapitalCityService(
            CapitalCityRepository capitalCityRepository,
            CityRepository cityRepository) {

        this.capitalCityRepository = capitalCityRepository;
        this.cityRepository = cityRepository;
    }

    // Default constructor for App.java
    public CapitalCityService() {
        this(new CapitalCityRepository(), new CityRepository());
    }

    // 1. Get capital cities worldwide.
    /**
     * Retrieves all capital cities worldwide,
     * ordered by population from largest to smallest.
     *
     * @return a list of all capital cities worldwide
     * @throws SQLException if a database access error occurs
     */

    public List<City> getCapitalCities() throws SQLException {
        return capitalCityRepository.getCapitalCities(0);
    }

    // 2. Report: Top N capital cities
    /**
     * Retrieves the top N most populated capital cities worldwide,
     * ordered by population from largest to smallest.
     *
     * @param topN the maximum number of capital cities to retrieve
     * @return a list of the top N capital cities worldwide
     * @throws IllegalArgumentException if topN is zero or negative
     * @throws SQLException if a database access error occurs
     */
    public List<City> getTopNCapitalCities(int topN) throws SQLException {
        if (topN <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        return capitalCityRepository.getCapitalCities(topN);
    }

    // 3. All capitals in a continent
    /**
     * Retrieves all capital cities in a specified continent,
     * ordered by population from largest to smallest.
     *
     * @param continent the continent to retrieve capital cities from
     * @return a list of all capital cities in the specified continent
     * @throws SQLException if a database access error occurs
     */

    public List<City> getCapitalCitiesByContinent(Continent continent)
            throws SQLException {
        return capitalCityRepository.getCapitalCitiesByContinent(continent, 0);
    }

    // 4. Top N capitals in a continent
    /**
     * Retrieves the top N capital cities in a given continent,
     * ordered by population in descending order.
     *
     * @param continent the continent to retrieve capital cities from
     * @param topN the maximum number of capital cities to return
     * @return a list of capital cities
     * @throws SQLException if a database error occurs
     */

    public List<City> getTopNCapitalCitiesByContinent(int topN, Continent continent)
            throws SQLException {

        if (topN <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (continent == null) {
            throw new IllegalArgumentException("Continent is required");
        }

        return capitalCityRepository.getCapitalCitiesByContinent(continent, topN);
    }

    // 5. All capitals in a region
    /**
     * Retrieves all capital cities in a specified region,
     * ordered by population from largest to smallest.
     *
     * @param region the region to retrieve capital cities from
     * @return a list of capital cities in the specified region
     * @throws SQLException if a database access error occurs
     */

    public List<City> getCapitalCitiesByRegion(String region)
            throws SQLException {
        return capitalCityRepository.getCapitalCitiesByRegion(region, 0);
    }

    // 6. Top N capitals in a region
    /**
     * Retrieves the top N most populated capital cities in a specified region,
     * ordered by population from largest to smallest.
     *
     * @param region the region to retrieve capital cities from
     * @param topN the maximum number of capital cities to retrieve
     * @return a list of the top N capital cities in the specified region
     * @throws IllegalArgumentException if topN is not positive or region is null
     * @throws SQLException if a database access error occurs
     */

    public List<City> getTopNCapitalCitiesByRegion(String region, int topN)
            throws SQLException {
        if (topN <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Region is required");
        }
        return capitalCityRepository.getCapitalCitiesByRegion(region, topN);
    }

}
