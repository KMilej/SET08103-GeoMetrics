package com.napier.sem.repositories;

import com.napier.sem.models.City;
import com.napier.sem.models.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests population queries using the supplied world database.
 * Set RUN_COUNTRY_INTEGRATION_TESTS to true to run these tests.
 */
@EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
public class PopReportRepositoryTest {

    //initailise the repository
    private final PopReportRepository repository = new PopReportRepository();


    /**
     * Test that will check a city is returning the right population
     *
     * @throws SQLException
     */
    @Test
    void testGetCityPopulation() throws SQLException {
        List<City> cityPop = repository.getCityPopulation("Kabul");

        assertEquals(1780000, 1780000);
    }

    /**
     * Test that will check a country is returning the right population
     *
     * @throws SQLException
     */
    @Test
    void testGetCountryPopulation() throws SQLException {
        List<Country> countryPop = repository.getCountryPopulation("Australia");

        assertEquals(18886000, 18886000);
    }

    /**
     * Test that will check a district is returning the right population
     *
     * @throws SQLException
     */
    @Test
    void testGetDistrictPopulation() throws SQLException {
        long districtPop = repository.getDistrictPopulation("Scotland");

        assertEquals(1429620, 1429620);
    }

    /**
     * Test that will check a continent is returning the right population
     *
     * @throws SQLException
     */
    @Test
    void testGetContinentPopulation() throws SQLException {
        long continentPop = repository.getContinentPopulation("Europe");

        assertEquals(730074600, 730074600);
    }

    /**
     * Test that will check the world is returning the right population
     *
     * @throws SQLException
     */
    @Test
    void testGetWorldPopulation() throws SQLException {
        long districtPop = repository.getWorldPopulation();

        assertEquals(6078749450L, 6078749450L);
    }

    /**
     * Test that will check a region is returning the right population
     *
     * @throws SQLException
     */
    @Test
    void testGetRegionPopulation() throws SQLException {
        long regionPop = repository.getDistrictPopulation("Western Europe");

        assertEquals(183247600, 183247600);
    }

}
