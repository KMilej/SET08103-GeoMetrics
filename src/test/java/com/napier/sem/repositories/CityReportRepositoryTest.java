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

    /**
     * Checks the number of British cities and the first and last country codes.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getCitiesByCountryByPopulation_filtersByCountryCode() throws SQLException {
        List<City> cities = repository.getCitiesByCountryByPopulation("GBR");

        assertEquals(81, cities.size());
        assertEquals("GBR", cities.get(0).getCountryCode());
        assertEquals("GBR", cities.get(cities.size() - 1).getCountryCode());
    }

    /**
     * Checks that an unknown district returns an empty list.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getCitiesByDistrictByPopulation_returnsEmptyListForUnknownDistrict() throws SQLException {
        List<City> cities = repository.getCitiesByDistrictByPopulation("Unknown district");

        assertTrue(cities.isEmpty());
    }

    /**
     * Checks that requesting one city returns the most populated city.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCitiesInWorld_limitsResultToOneCity() throws SQLException {
        List<City> cities = repository.getTopNCitiesInWorld(1);

        assertEquals(1, cities.size());
        assertEquals("Mumbai (Bombay)", cities.get(0).getName());
    }

    /**
     * Checks the limit and population order of two European cities.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCitiesByContinent_ordersTwoCitiesByPopulation() throws SQLException {
        List<City> cities = repository.getTopNCitiesByContinent(2, Continent.EUROPE);

        assertEquals(2, cities.size());
        assertEquals("Moscow", cities.get(0).getName());
        assertTrue(cities.get(0).getPopulation() >= cities.get(1).getPopulation());
    }

    /**
     * Checks the first and third cities in a top three regional result.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCitiesByRegion_returnsThreeLargestCities() throws SQLException {
        List<City> cities = repository.getTopNCitiesByRegion(3, "Western Europe");

        assertEquals(3, cities.size());
        assertEquals("Berlin", cities.get(0).getName());
        assertEquals("Hamburg", cities.get(2).getName());
    }

    /**
     * Checks that the country filter also works for France.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCitiesByCountry_acceptsAnotherCountryCode() throws SQLException {
        List<City> cities = repository.getTopNCitiesByCountry(2, "FRA");

        assertEquals(2, cities.size());
        assertEquals("Paris", cities.get(0).getName());
        assertEquals("FRA", cities.get(1).getCountryCode());
    }

    /**
     * Checks that requesting ten Scottish cities returns the four available cities.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCitiesByDistrict_returnsFewerCitiesWhenNIsTooLarge() throws SQLException {
        List<City> cities = repository.getTopNCitiesByDistrict(10, "Scotland");

        assertEquals(4, cities.size());
        assertEquals("Dundee", cities.get(3).getName());
        assertEquals("Scotland", cities.get(3).getDistrict());
    }
}
