package com.napier.sem.services;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.repositories.CapitalCityRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapitalCityServiceTest {

    // Create a test repository with no database connection
    @Mock
    private CapitalCityRepository capitalCityRepository;

    // Create the service and injects the fake repository
    @InjectMocks
    private CapitalCityService capitalCityService;

    // Testing the method: getCapitalCities
    // Test 1.1 getCapitalCities with valid result

    /**
     * Tests that the service returns a list of capital cities.
     */
    @Test
    void testGetCapitalCitiesValid() throws SQLException {
        //Arrange
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City(1, "London", "GBR", "England", 7285000));

        when(capitalCityRepository.getCapitalCities(0))
                .thenReturn(expectedCities);

        //Act
        List<City> actualCities = capitalCityService.getCapitalCities();

        //Assert
        assertEquals(expectedCities, actualCities);

        verify(capitalCityRepository).getCapitalCities(0);
    }

    // Test 1.2 Returns an empty list

    /**
     * Tests that an empty list is returned when no capital cities are found.
     */
    @Test
    void testGetCapitalCitiesEmpty() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();

        when(capitalCityRepository.getCapitalCities(0))
                .thenReturn(expectedCities);

        // Act
        List<City> actualCities = capitalCityService.getCapitalCities();

        // Assert
        assertTrue(actualCities.isEmpty());

        verify(capitalCityRepository).getCapitalCities(0);
    }

    //Test 1.3

    /**
     * Tests that a database exception is propagated by the service.
     */
    @Test
    void testGetCapitalCitiesSQLException() throws SQLException {

        // Arrange
        when(capitalCityRepository.getCapitalCities(0))
                .thenThrow(new SQLException("Database error"));

        // Act + Assert
        try {
            capitalCityService.getCapitalCities();
            fail("Expected SQLException");

        } catch (SQLException e) {
            assertEquals("Database error", e.getMessage());
        }

        verify(capitalCityRepository).getCapitalCities(0);
    }
    // Testing the method: getTopNCapitalCities(int topN)
    // Test 2.1  -  valid top N

    /**
     * Tests that a valid topN returns the expected capital cities.
     */
    @Test
    void testTopNCapitalCitiesValid() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City(1, "London", "GBR", "England", 7285000));

        when(capitalCityRepository.getCapitalCities(10))
                .thenReturn(expectedCities);

        // Act
        List<City> actualCities = capitalCityService.getTopNCapitalCities(10);

        // Assert
        assertEquals(expectedCities, actualCities);

        verify(capitalCityRepository).getCapitalCities(10);
    }

    // Test 2.2  - when N is zero

    /**
     * Tests that zero topN throws an IllegalArgumentException.
     */
    @Test
    void testTopNCapitalCitiesZero() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCities(0);
            fail("Expected IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }

    // Test 2.3  when N > 0

    /**
     * Tests that a negative topN throws an IllegalArgumentException.
     */
    @Test
    void testTopNCapitalCitiesNegative() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCities(-5);
            fail("Expected IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }
    // Test 2.4 when results are not found

    /**
     * Tests that an empty list is returned when no capital cities are found.
     */
    @Test
    void testTopNCapitalCitiesEmpty() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();

        when(capitalCityRepository.getCapitalCities(10))
                .thenReturn(expectedCities);

        // Act
        List<City> actualCities = capitalCityService.getTopNCapitalCities(10);

        // Assert
        assertTrue(actualCities.isEmpty());

        verify(capitalCityRepository).getCapitalCities(10);
    }
    // Test 2.5  database exception

    /**
     * Tests that a database exception is propagated by the service.
     */
    @Test
    void testTopNCapitalCitiesSQLException() throws SQLException {

        // Arrange
        when(capitalCityRepository.getCapitalCities(10))
                .thenThrow(new SQLException("Database error"));

        // Act + Assert
        try {
            capitalCityService.getTopNCapitalCities(10);
            fail("Expected SQLException");

        } catch (SQLException e) {
            assertEquals("Database error", e.getMessage());
        }

        verify(capitalCityRepository).getCapitalCities(10);
    }


    // Testing the method:getCapitalCitiesByContinent(Continent continent)
    //  Test 3.1 - valid continent

    /**
     * Tests that a valid continent returns the expected capital cities.
     */
    @Test
    void testGetCapitalCitiesByContinentValid() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City(1, "Cairo", "EGY", "Kairo", 6789479));

        when(capitalCityRepository.getCapitalCitiesByContinent(Continent.AFRICA, 0))
                .thenReturn(expectedCities);

        // Act
        List<City> actualCities =
                capitalCityService.getCapitalCitiesByContinent(Continent.AFRICA);

        // Assert
        assertEquals(expectedCities, actualCities);

        verify(capitalCityRepository)
                .getCapitalCitiesByContinent(Continent.AFRICA, 0);
    }
    //  Test 3.2 - invalid continent

    /**
     * Tests that a null continent throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testGetCapitalCitiesByContinentNull() throws SQLException {

        try {
            capitalCityService.getCapitalCitiesByContinent(null);
            fail("Expected IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Continent is required", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }
    //  Test 3.3 - no results

    /**
     * Tests that an empty list is returned when no capital cities are found.
     */
    @Test
    void testGetCapitalCitiesByContinentEmpty() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();

        when(capitalCityRepository.getCapitalCitiesByContinent(Continent.ANTARCTICA, 0))
                .thenReturn(expectedCities);

        // Act
        List<City> actualCities =
                capitalCityService.getCapitalCitiesByContinent(Continent.ANTARCTICA);

        // Assert
        assertTrue(actualCities.isEmpty());

        verify(capitalCityRepository)
                .getCapitalCitiesByContinent(Continent.ANTARCTICA, 0);
    }
    // Test 3.4 - sql exception

    /**
     * Tests that a database exception is propagated by the service.
     */
    @Test
    void testGetCapitalCitiesByContinentSQLException() throws SQLException {

        // Arrange
        when(capitalCityRepository.getCapitalCitiesByContinent(Continent.AFRICA, 0))
                .thenThrow(new SQLException("Database error"));

        // Act + Assert
        try {
            capitalCityService.getCapitalCitiesByContinent(Continent.AFRICA);
            fail("Expected SQLException");

        } catch (SQLException e) {
            assertEquals("Database error", e.getMessage());
        }

        verify(capitalCityRepository)
                .getCapitalCitiesByContinent(Continent.AFRICA, 0);
    }

    // Testing the method: getTopNCapitalCitiesByContinent
    // Test 4.1 valid input

    /**
     * Tests that valid inputs return the expected capital cities.
     */
    @Test
    void testValidContinentAndTopN() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City(1, "Cairo", "EGY", "Kairo", 6789479));

        when(capitalCityRepository.getCapitalCitiesByContinent(Continent.AFRICA, 10))
                .thenReturn(expectedCities);

        // Act
        List<City> actualCities =
                capitalCityService.getTopNCapitalCitiesByContinent(10, Continent.AFRICA);

        // Assert
        assertEquals(expectedCities, actualCities);

        verify(capitalCityRepository)
                .getCapitalCitiesByContinent(Continent.AFRICA, 10);
    }

    // Test 4.2

    /**
     * Tests that a topN value of zero throws an exception.
     */
    @Test
    void testContinentZeroTopN() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCitiesByContinent(0, Continent.AFRICA);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }

    // Test 4.3

    /**
     * Tests that a negative topN value throws an exception.
     */
    @Test
    void testContinentNegativeTopN() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCitiesByContinent(-5, Continent.AFRICA);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }

    //Test 4.4

    /**
     * Tests that a null continent throws an exception.
     */
    @Test
    void testNullContinent() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCitiesByContinent(10, null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Continent is required", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }

    //Test 4.5

    /**
     * Tests that an empty list is returned when no capital cities are found.
     */
    @Test
    void testContinentEmptyResult() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();

        when(capitalCityRepository.getCapitalCitiesByContinent(Continent.ANTARCTICA, 10))
                .thenReturn(expectedCities);

        // Act
        List<City> result =
                capitalCityService.getTopNCapitalCitiesByContinent(10, Continent.ANTARCTICA);

        // Assert
        assertTrue(result.isEmpty());

        verify(capitalCityRepository)
                .getCapitalCitiesByContinent(Continent.ANTARCTICA, 10);
    }


    // TESTING the method: All capitals in a region
    // TEST 5.1: Valid region

    /**
     * Tests that a valid region returns the expected capital cities.
     */

    @Test
    void testGetCapitalCitiesByRegionValid() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City(1, "Paris", "FRA", "Ile-de-France", 2000000));

        when(capitalCityRepository.getCapitalCitiesByRegion("Western Europe", 0))
                .thenReturn(expectedCities);

        // Act
        List<City> actualCities =
                capitalCityService.getCapitalCitiesByRegion("Western Europe");

        // Assert
        assertEquals(expectedCities, actualCities);

        verify(capitalCityRepository)
                .getCapitalCitiesByRegion("Western Europe", 0);
    }

    // TEST 5.2: Empty result

    /**
     * Tests that an empty list is returned when no capital cities are found.
     */

    @Test
    void testGetCapitalCitiesByRegionEmpty() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();

        when(capitalCityRepository.getCapitalCitiesByRegion("Unknown Region", 0))
                .thenReturn(expectedCities);

        // Act
        List<City> result =
                capitalCityService.getCapitalCitiesByRegion("Unknown Region");

        // Assert
        assertTrue(result.isEmpty());

        verify(capitalCityRepository)
                .getCapitalCitiesByRegion("Unknown Region", 0);
    }

    // TEST 5.3: Repository exception

    /**
     * Tests that a database exception is propagated by the service.
     */

    @Test
    void testGetCapitalCitiesByRegionSQLException() throws SQLException {

        // Arrange
        when(capitalCityRepository.getCapitalCitiesByRegion("Western Europe", 0))
                .thenThrow(new SQLException("Database error"));

        // Act + Assert
        try {
            capitalCityService.getCapitalCitiesByRegion("Western Europe");
            fail("Expected SQLException");
        } catch (SQLException e) {
            assertEquals("Database error", e.getMessage());
        }

        verify(capitalCityRepository)
                .getCapitalCitiesByRegion("Western Europe", 0);
    }


    // TEST 6.1: Valid region and valid topN

    /**
     * Tests that valid region and topN inputs return the expected capital cities
     * and that the repository is called with the correct parameters.
     */

    @Test
    void testValidRegionAndTopN() throws SQLException {

        // Arrange
        List<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City(1, "Paris", "FRA", "Ile-de-France", 2000000));

        when(capitalCityRepository.getCapitalCitiesByRegion("Western Europe", 10))
                .thenReturn(expectedCities);

        // Act
        List<City> actualCities =
                capitalCityService.getTopNCapitalCitiesByRegion("Western Europe", 10);

        // Assert
        assertEquals(1, actualCities.size());
        assertEquals("Paris", actualCities.get(0).getName());

        verify(capitalCityRepository)
                .getCapitalCitiesByRegion("Western Europe", 10);
    }


    // TEST 6.2: Zero topN should throw an exception

    /**
     * Tests that a topN value of zero throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testZeroTopN() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCitiesByRegion("Western Europe", 0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }


    // TEST 6.3: Negative topN should throw an exception

    /**
     * Tests that a negative topN value throws an IllegalArgumentException
     * without calling the repository.
     */

    @Test
    void testNegativeTopN() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCitiesByRegion("Western Europe", -5);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid input", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }


    // TEST 6.4: Null region should throw an exception

    /**
     * Tests that a null region throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testNullRegion() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCitiesByRegion(null, 10);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Region is required", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }

    // TEST 6.5: Empty region should throw an exception

    /**
     * Tests that an empty region string throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testEmptyRegion() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCitiesByRegion("", 10);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Region is required", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }

    // TEST 6.6: whitespace region should throw an exception

    /**
     * Tests that a whitespace-only region throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testWhitespaceRegion() throws SQLException {

        try {
            capitalCityService.getTopNCapitalCitiesByRegion("   ", 10);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Region is required", e.getMessage());
        }

        verifyNoInteractions(capitalCityRepository);
    }
}