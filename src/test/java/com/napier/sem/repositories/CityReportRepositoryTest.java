/****************************************************************
 * Project: GeoMetrics
 * File:    CityReportRepositoryTest.java
 * Author:  Kamil Milej
 ***************************************************************/

package com.napier.sem.repositories;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests UC02 database queries using the supplied world database.
 * Set RUN_CITY_INTEGRATION_TESTS to true to run these tests.
 */
@EnabledIfEnvironmentVariable(named = "RUN_CITY_INTEGRATION_TESTS", matches = "true")
class CityReportRepositoryTest {

    private final CityReportRepository repository = new CityReportRepository();

    /**
     * Checks the number of cities and the most populated city.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCitiesByPopulation_returnsLargestCityFirst() throws SQLException {
        List<City> cities = repository.getAllCitiesByPopulation();

        assertEquals(4079, cities.size());
        assertEquals("Mumbai (Bombay)", cities.get(0).getName());
        assertEquals(10500000, cities.get(0).getPopulation());
    }

    /**
     * Checks that NORTH_AMERICA works as a continent filter.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getCitiesByContinentByPopulation_acceptsContinentWithTwoWords() throws SQLException {
        List<City> cities = repository.getCitiesByContinentByPopulation(Continent.NORTH_AMERICA);

        assertEquals("Ciudad de México", cities.get(0).getName());
        assertEquals("MEX", cities.get(0).getCountryCode());
    }

    /**
     * Checks results for the Western Europe region.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getCitiesByRegionByPopulation_returnsWesternEuropeanCities() throws SQLException {
        List<City> cities = repository.getCitiesByRegionByPopulation("Western Europe");

        assertEquals(186, cities.size());
        assertEquals("Berlin", cities.get(0).getName());
        assertEquals("DEU", cities.get(0).getCountryCode());
    }














}
