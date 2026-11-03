package com.napier.sem.repositories;

import com.napier.sem.models.Continent;
import com.napier.sem.models.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests country database queries using the supplied world database.
 * Set RUN_COUNTRY_INTEGRATION_TESTS to true to run these tests.
 */
@EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
class CountryRepositoryTest {

    private final CountryRepository repository = new CountryRepository();

    /**
     * Checks the number of countries and the most populated country.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCountries_returnsLargestCountryFirst() throws SQLException {
        List<Country> countries = repository.getAllCountries();

        assertEquals(239, countries.size());
        assertEquals("CHN", countries.get(0).getCode());
        assertEquals("China", countries.get(0).getName());
        assertEquals(1277558000, countries.get(0).getPopulation());
        assertEquals("Peking", countries.get(0).getCapitalName());
    }

    /**
     * Checks that every country is ordered from largest to smallest population.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCountries_ordersByPopulationDescending() throws SQLException {
        List<Country> countries = repository.getAllCountries();

        for (int i = 1; i < countries.size(); i++) {
            assertTrue(countries.get(i - 1).getPopulation() >= countries.get(i).getPopulation());
        }
    }

    /**
     * Checks that Antarctica is still returned with no capital.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCountries_keepsCountryWithoutCapital() throws SQLException {
        Country antarctica = repository.getAllCountries().stream()
                .filter(c -> c.getCode().equals("ATA"))
                .findFirst()
                .orElseThrow();

        assertEquals("Antarctica", antarctica.getName());
        assertNull(antarctica.getCapitalName());
    }

    /**
     * Checks the number of European countries and the most populated one.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCountriesByContinent_returnsEuropeanCountries() throws SQLException {
        List<Country> countries = repository.getAllCountriesByContinent(Continent.EUROPE);

        assertEquals(46, countries.size());
        assertEquals("RUS", countries.get(0).getCode());
        assertEquals(Continent.EUROPE, countries.get(0).getContinent());
        assertEquals("Moscow", countries.get(0).getCapitalName());
    }

    /**
     * Checks the number of Asian countries and the most populated one.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCountriesByContinent_returnsAsianCountries() throws SQLException {
        List<Country> countries = repository.getAllCountriesByContinent(Continent.ASIA);

        assertEquals(51, countries.size());
        assertEquals("China", countries.get(0).getName());
    }

    /**
     * Checks that NORTH_AMERICA works as a continent filter.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCountriesByContinent_acceptsContinentWithTwoWords() throws SQLException {
        List<Country> countries = repository.getAllCountriesByContinent(Continent.NORTH_AMERICA);

        assertEquals("USA", countries.get(0).getCode());
        assertEquals(Continent.NORTH_AMERICA, countries.get(0).getContinent());
    }

    /**
     * Checks the first two countries in the Western Europe region.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCountriesByRegion_returnsWesternEuropeanCountries() throws SQLException {
        List<Country> countries = repository.getAllCountriesByRegion("Western Europe");

        assertEquals("Germany", countries.get(0).getName());
        assertEquals("France", countries.get(1).getName());
        assertEquals("Western Europe", countries.get(0).getRegion());
    }

    /**
     * Checks that an unknown region returns an empty list.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getAllCountriesByRegion_returnsEmptyListForUnknownRegion() throws SQLException {
        List<Country> countries = repository.getAllCountriesByRegion("Unknown region");

        assertTrue(countries.isEmpty());
    }

    /**
     * Checks that requesting one country returns the most populated country.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCountriesInWorld_limitsResultToOneCountry() throws SQLException {
        List<Country> countries = repository.getTopNCountriesInWorld(1);

        assertEquals(1, countries.size());
        assertEquals("China", countries.get(0).getName());
    }

    /**
     * Checks the order of the three most populated countries in the world.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCountriesInWorld_returnsThreeLargestCountries() throws SQLException {
        List<Country> countries = repository.getTopNCountriesInWorld(3);

        assertEquals(3, countries.size());
        assertEquals("China", countries.get(0).getName());
        assertEquals("India", countries.get(1).getName());
        assertEquals("United States", countries.get(2).getName());
    }

    /**
     * Checks the limit and population order of two European countries.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCountriesInWorldByContinent_ordersTwoCountriesByPopulation() throws SQLException {
        List<Country> countries = repository.getTopNCountriesInWorldByContinent(2, Continent.EUROPE);

        assertEquals(2, countries.size());
        assertEquals("Russian Federation", countries.get(0).getName());
        assertTrue(countries.get(0).getPopulation() >= countries.get(1).getPopulation());
    }

    /**
     * Checks that zero and negative values of N are rejected for a continent.
     */
    @Test
    void getTopNCountriesInWorldByContinent_rejectsZeroAndNegativeN() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.getTopNCountriesInWorldByContinent(0, Continent.EUROPE));
        assertThrows(IllegalArgumentException.class,
                () -> repository.getTopNCountriesInWorldByContinent(-1, Continent.EUROPE));
    }

    /**
     * Checks the top two countries in the Western Europe region.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    void getTopNCountriesInWorldByRegion_returnsTwoLargestCountries() throws SQLException {
        List<Country> countries = repository.getTopNCountriesInWorldByRegion(2, "Western Europe");

        assertEquals(2, countries.size());
        assertEquals("Germany", countries.get(0).getName());
        assertEquals("France", countries.get(1).getName());
    }

    /**
     * Checks that zero and negative values of N are rejected for a region.
     */
    @Test
    void getTopNCountriesInWorldByRegion_rejectsZeroAndNegativeN() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.getTopNCountriesInWorldByRegion(0, "Western Europe"));
        assertThrows(IllegalArgumentException.class,
                () -> repository.getTopNCountriesInWorldByRegion(-1, "Western Europe"));
    }
}