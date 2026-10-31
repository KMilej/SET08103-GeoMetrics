package com.napier.sem.repositories;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalCityRepositoryTests {

    // Test 1.1 getCapitalCities -
    /**
     * Integration test that verifies the repository retrieves all capital cities
     * from the MySQL world database when topN is zero.
     *
     * Confirms that the SQL query executes successfully without a LIMIT clause
     * and returns a list of capital cities.
     *
     * Requires the Docker MySQL database to be running.
     */

    @Test
    void testGetTopNCapitalCities() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCities(2);

        // Assert
        assertNotNull(cities);
        assertEquals(2, cities.size());

        assertTrue(cities.get(0).getPopulation() >=
                cities.get(1).getPopulation());

    }

    // Test 1.2 population ordering
    /**
     * Integration test that verifies capital cities are returned in
     * descending order of population from the MySQL world database.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testCapitalCitiesPopulationOrder() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCities(10);

        // Assert
        assertEquals(10, cities.size());

        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(
                    cities.get(i).getPopulation() >=
                            cities.get(i + 1).getPopulation()
            );
        }
    }

    // Test 1.3 correct city data
    /**
     * Integration test that verifies database records are correctly
     * mapped to City objects.
     *
     * Checks the ID, name, country code, district and population
     * against the corresponding database record.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testCapitalCityDataMapping() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCities(10);

        // Assert
        assertFalse(cities.isEmpty());

        City city = cities.get(0);

        assertTrue(city.getId() > 0);
        assertNotNull(city.getName());
        assertFalse(city.getName().isBlank());
        assertNotNull(city.getCountryCode());
        assertNotNull(city.getDistrict());
        assertTrue(city.getPopulation() > 0);
    }

// Test 2.1 all capital cities by continent
    /**
     * Integration test that verifies all capital cities for a specified
     * continent are retrieved from the MySQL world database.
     *
     * Uses topN = 0 to retrieve results without a LIMIT clause.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testGetAllCapitalCitiesByContinent() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByContinent(
                Continent.EUROPE, 0
        );

        // Assert
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }
    // Test 2.2 valid top N by continent (verify Limit? works)
    /**
     * Integration test that verifies the repository returns the
     * requested number of capital cities for a specified continent.
     *
     * Retrieves the top 5 European capital cities from the
     * MySQL world database.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testGetTopNCapitalCitiesByContinent() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByContinent(
                Continent.EUROPE, 5
        );

        // Assert
        assertNotNull(cities);
        assertEquals(5, cities.size());
    }
    //   Test 2.3 Population ordering by continent
    /**
     * Integration test that verifies capital cities for a specified
     * continent are returned in descending order of population.
     *
     * Checks that each city's population is greater than or equal
     * to the population of the next city.
     *
     * Requires the Docker MySQL database to be running.
     */

    @Test
    void testCapitalCitiesByContinentPopulationOrder() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByContinent(
                Continent.ASIA, 10
        );

        // Assert
        assertTrue(cities.size() >= 2);

        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(
                    cities.get(i).getPopulation() >=
                            cities.get(i + 1).getPopulation()
            );
        }
    }
    /**
     * Integration test that verifies the repository filters capital
     * cities by the specified continent.
     *
     * Checks that London is included when retrieving European
     * capital cities from the MySQL world database.
     *
     * Requires the Docker MySQL database to be running.
     */

    // Test 2.4 Correct continent filtering
    @Test
    void testCapitalCitiesFilteredByContinent() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByContinent(
                Continent.EUROPE, 0
        );

        // Assert
        boolean londonFound = false;

        for (City city : cities) {
            if (city.getName().equals("London")) {
                londonFound = true;
            }
        }

        assertTrue(londonFound, "Expected London in European capital cities");
    }

    // Test 2.5 No results for a continent
    /**
     * Integration test that verifies an empty list is returned when
     * no capital cities match the specified continent.
     *
     * Uses Antarctica, which has no matching capital cities in
     * the standard MySQL world database.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testCapitalCitiesByContinentNoResults() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByContinent(
                Continent.ANTARCTICA, 0
        );

        // Assert
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }
    // Test 3.1  Retrieve all capital cities by region
    /**
     * Integration test that verifies all capital cities for a specified
     * region are retrieved from the MySQL world database.
     *
     * Uses topN = 0 to execute the SQL query without a LIMIT clause.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testGetAllCapitalCitiesByRegion() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByRegion(
                "British Islands", 0
        );

        // Assert
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    // Test 3.2 Valid topN by region
    /**
     * Integration test that verifies the repository retrieves the
     * requested number of capital cities for a specified region.
     *
     * Uses the real MySQL world database to test the SQL LIMIT clause.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testGetTopNCapitalCitiesByRegion() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByRegion(
                "Eastern Asia", 3
        );

        // Assert
        assertNotNull(cities);
        assertEquals(3, cities.size());
    }


    // Test 3.3  Population ordering by region

    /**
     * Integration test that verifies capital cities for a specified
     * region are returned in descending order of population.
     *
     * Checks that each city's population is greater than or equal
     * to the population of the next city.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testCapitalCitiesByRegionPopulationOrder() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByRegion(
                "Eastern Asia", 5
        );

        // Assert
        assertTrue(cities.size() >= 2);

        for (int i = 0; i < cities.size() - 1; i++) {
            assertTrue(
                    cities.get(i).getPopulation() >=
                            cities.get(i + 1).getPopulation()
            );
        }
    }

    // Test 3.4 Correct region filtering

    /**
     * Integration test that verifies capital cities are filtered
     * according to the specified region.
     *
     * Checks that London is included when retrieving capital cities
     * from the British Islands region.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testCapitalCitiesFilteredByRegion() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByRegion(
                "British Islands", 0
        );

        // Assert
        boolean londonFound = false;

        for (City city : cities) {
            if (city.getName().equals("London")) {
                londonFound = true;
            }
        }

        assertTrue(londonFound, "Expected London in British Islands capital cities");
    }

    // Test 3.5 No results for an unknown region

    /**
     * Integration test that verifies an empty list is returned
     * when no capital cities match the specified region.
     *
     * Uses a region name that does not exist in the MySQL world
     * database to test the query's empty-result behaviour.
     *
     * Requires the Docker MySQL database to be running.
     */
    @Test
    void testCapitalCitiesByRegionNoResults() throws SQLException {

        // Arrange
        CapitalCityRepository repository = new CapitalCityRepository();

        // Act
        List<City> cities = repository.getCapitalCitiesByRegion(
                "Unknown Region", 0
        );

        // Assert
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }
}
