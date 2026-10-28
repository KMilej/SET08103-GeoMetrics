package com.napier.sem.services;

import com.napier.sem.models.City;
import com.napier.sem.repositories.CapitalCityRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapitalCityServiceTest {

    // Create a fake repository with no database connection
    @Mock
    private CapitalCityRepository capitalCityRepository;

    // Create the service and injects the fake repository
    @InjectMocks
    private CapitalCityService capitalCityService;


    // TESTING the method: All capitals in a region
    // TEST 5.1: Valid region
    /**
     * Tests that a valid region returns the expected capital cities.
     */

    @Test
    void testGetCapitalCitiesByRegionValid() throws SQLException {

    List<City> expectedCities = List.of(
            new City(1, "Paris", "FRA", "Ile-de-France", 2000000)
    );

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
        when(capitalCityRepository.getCapitalCitiesByRegion("Unknown Region", 0))
                .thenReturn(List.of());

        // Act
        List<City> result =
                capitalCityService.getCapitalCitiesByRegion("Unknown Region");

        // Assert
        assertTrue(result.isEmpty());
    }

    // TEST 5.3: Repository exception
    /**
     * Tests that a database exception is propagated by the service.
     */

    void testGetCapitalCitiesByRegionSQLException() throws SQLException {

        // Arrange
        when(capitalCityRepository.getCapitalCitiesByRegion("Western Europe", 0))
                .thenThrow(new SQLException("Database error"));

        // Act + Assert
        assertThrows(SQLException.class,
                () -> capitalCityService.getCapitalCitiesByRegion("Western Europe"));
    }


    // TEST 6.1: Valid region and valid topN

    /**
     * Tests that valid region and topN inputs return the expected capital cities
     * and that the repository is called with the correct parameters.
     */

    @Test
    void testValidRegionAndTopN() throws SQLException {

        // ARRANGE: Prepare the test data
        List<City> expectedCities = List.of(
                new City(1, "Paris", "FRA", "Ile-de-France", 2000000)
        );

        // telling Mockito what the repository should return
        when(capitalCityRepository.getCapitalCitiesByRegion("Western Europe", 10))
                .thenReturn(expectedCities);

        // ACT: Call the service method
        List<City> actualCities =
                capitalCityService.getTopNCapitalCitiesByRegion("Western Europe", 10);

        // ASSERT: Check the returned data
        assertEquals(1, actualCities.size());
        assertEquals("Paris", actualCities.get(0).getName());

        // VERIFY: Check the correct repository method was called
        verify(capitalCityRepository)
                .getCapitalCitiesByRegion("Western Europe", 10);
    }


    // TEST 6.2: Zero topN should throw an exception

    /**
     * Tests that a topN value of zero throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testZeroTopN() {

        // ACT + ASSERT
        assertThrows(IllegalArgumentException.class,
                () -> capitalCityService.getTopNCapitalCitiesByRegion("Western Europe", 0));

        // Repository should not be called
        verifyNoInteractions(capitalCityRepository);
    }


    // TEST 6.3: Negative topN should throw an exception

    /**
     * Tests that a negative topN value throws an IllegalArgumentException
     * without calling the repository.
     */

    @Test
    void testNegativeTopN() {

        // ACT + ASSERT
        assertThrows(IllegalArgumentException.class,
                () -> capitalCityService.getTopNCapitalCitiesByRegion("Western Europe", -5));

        // Repository should not be called
        verifyNoInteractions(capitalCityRepository);
    }


    // TEST 6.4: Null region should throw an exception

    /**
     * Tests that a null region throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testNullRegion() {

        // ACT + ASSERT
        assertThrows(IllegalArgumentException.class,
                () -> capitalCityService.getTopNCapitalCitiesByRegion(null, 10));

        // Repository should not be called
        verifyNoInteractions(capitalCityRepository);
    }

    // TEST 6.5: Empty region should throw an exception

    /**
     * Tests that an empty region string throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testEmptyRegion() {
        assertThrows(IllegalArgumentException.class,
                () -> capitalCityService.getTopNCapitalCitiesByRegion("", 10));

        verifyNoInteractions(capitalCityRepository);
    }

    // TEST 6.6: whitespace region should throw an exception

    /**
     * Tests that a whitespace-only region throws an IllegalArgumentException
     * without calling the repository.
     */
    @Test
    void testWhitespaceRegion() {
        assertThrows(IllegalArgumentException.class,
                () -> capitalCityService.getTopNCapitalCitiesByRegion("   ", 10));

        verifyNoInteractions(capitalCityRepository);
    }
}