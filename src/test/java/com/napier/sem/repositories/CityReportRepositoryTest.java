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
        assertEquals("Mexico", cities.get(0).getCountryName());
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
        assertEquals("Germany", cities.get(0).getCountryName());
    }

    /**
     * Checks the number of British cities and the first and last country names.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getCitiesByCountryByPopulation_filtersByCountryName() throws SQLException {
        List<City> cities = repository.getCitiesByCountryByPopulation("United Kingdom");

        assertEquals(81, cities.size());
        assertEquals("United Kingdom", cities.get(0).getCountryName());
        assertEquals("United Kingdom", cities.get(cities.size() - 1).getCountryName());
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
    void getTopNCitiesByCountry_acceptsAnotherCountryName() throws SQLException {
        List<City> cities = repository.getTopNCitiesByCountry(2, "France");

        assertEquals(2, cities.size());
        assertEquals("Paris", cities.get(0).getName());
        assertEquals("France", cities.get(1).getCountryName());
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


    /**
     * Checks that cities are ordered by population
     * from highest to lowest.
     *
     * @throws SQLException if database access fails
     */
    @Test
    void getAllCitiesByPopulation_checksDescendingOrder()
            throws SQLException {

        List<City> cities = repository.getAllCitiesByPopulation();

        assertTrue(!cities.isEmpty());

        for (int i = 1; i < cities.size(); i++) {
            assertTrue(
                    cities.get(i - 1).getPopulation()
                            >= cities.get(i).getPopulation()
            );
        }
    }

    /**
     * Checks that requesting more cities than available
     * returns all existing cities.
     *
     * @throws SQLException if database access fails
     */
    @Test
    void getTopNCitiesInWorld_largeNReturnsAll()
            throws SQLException {

        List<City> allCities = repository.getAllCitiesByPopulation();
        List<City> topCities = repository.getTopNCitiesInWorld(100000);

        assertEquals(allCities.size(), topCities.size());
    }

    /**
     * Checks that an unknown country
     * returns an empty list.
     *
     * @throws SQLException if database access fails
     */
    @Test
    void getCitiesByCountryByPopulation_unknownCountry()
            throws SQLException {

        List<City> cities =
                repository.getCitiesByCountryByPopulation("Unknown Country");

        assertTrue(cities.isEmpty());
    }

    /**
     * Checks that a Top N request greater than the
     * available cities returns only existing records.
     *
     * @throws SQLException if database access fails
     */
    @Test
    void getTopNCitiesByCountry_largeNReturnsAvailable()
            throws SQLException {

        List<City> allCities =
                repository.getCitiesByCountryByPopulation("United Kingdom");

        List<City> topCities =
                repository.getTopNCitiesByCountry(100000, "United Kingdom");

        assertEquals(allCities.size(), topCities.size());
    }
}
