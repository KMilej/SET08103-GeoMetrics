package com.napier.sem.services;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import com.napier.sem.repositories.PopulationRepository;
import com.napier.sem.repositories.PopulationRepository.PopulationDistributionData;

class PopulationDistributionServiceTest {

    // Repository used to retrieve population data from the database
    private final PopulationRepository repository =
            new PopulationRepository();

    // Sample valid population data for unit testing
    private static final List<PopulationDistributionData> MOCK_DATA = List.of(
            new PopulationDistributionData("United Kingdom", 60000000L, 48000000L),
            new PopulationDistributionData("France", 50000000L, 30000000L),
            new PopulationDistributionData("Germany", 80000000L, 60000000L),
            new PopulationDistributionData("Zero Population", 0L, 0L),
            new PopulationDistributionData("Invalid Total Population", 50000L, 100000L),
            new PopulationDistributionData("No City Population", 20000000L, 0L)
    );

    // Sample invalid population data for unit testing
    private static final List<PopulationDistributionData> INVALID_MOCK_DATA = List.of(
            new PopulationDistributionData("City Exceeds Total", 1000000L, 1200000L),
            new PopulationDistributionData("Negative Total", -500000L, 200000L),
            new PopulationDistributionData("Negative City", 1000000L, -200000L),
            new PopulationDistributionData("Zero Total", 0L, 1000L)
    );

    /**
     * Test an invalid scope throws an exception
     */
    @Test
    void testInvalidScope() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.getPopulationDistributionData(
                        "CITY", "Edinburgh"
                ));
    }

    /**
     * Test a null or empty scope throws an exception
     */
    @Test
    void testNullOrEmptyScope() {
        // Check null scope
        assertThrows(IllegalArgumentException.class,
                () -> repository.getPopulationDistributionData(
                        null, "Europe"
                ));

        // Check empty scope
        assertThrows(IllegalArgumentException.class,
                () -> repository.getPopulationDistributionData(
                        "", "Europe"
                ));

        // Check whitespace scope
        assertThrows(IllegalArgumentException.class,
                () -> repository.getPopulationDistributionData(
                        "   ", "Europe"
                ));
    }

    /**
     * Test a null or empty location throws an exception
     */
    @Test
    void testNullOrEmptyLocation() {
        // Check null location
        assertThrows(IllegalArgumentException.class,
                () -> repository.getPopulationDistributionData(
                        "CONTINENT", null
                ));

        // Check empty locatio
        assertThrows(IllegalArgumentException.class,
                () -> repository.getPopulationDistributionData(
                        "CONTINENT", ""

                ));

        // Check whitespace locatio
        assertThrows(IllegalArgumentException.class,
                () -> repository.getPopulationDistributionData(
                        "CONTINENT", "    "

                ));
    }


    /**
     * Test if null, empty and whitespace region throws an exception
     */
    @Test
    void testInvalidRegion() {
        // Empty
        assertThrows(IllegalArgumentException.class,
                () -> PopulationDistributionService
                        .getUrbanAndRuralPopulationByRegion(""));
        // Null
        assertThrows(IllegalArgumentException.class,
                () -> PopulationDistributionService
                        .getUrbanAndRuralPopulationByRegion(null));
        // Whitespace
        assertThrows(IllegalArgumentException.class,
                () -> PopulationDistributionService
                        .getUrbanAndRuralPopulationByRegion("   "));
    }

    /**
     * Test if empty, null and whitespace country name throws an exception
     */
    @Test
    void testInvalidCountryName() {
        // Empty
        assertThrows(IllegalArgumentException.class,
                () -> PopulationDistributionService
                        .getUrbanAndRuralPopulationByCountry(""));
        // Null
        assertThrows(IllegalArgumentException.class,
                () -> PopulationDistributionService
                        .getUrbanAndRuralPopulationByCountry(null));
        // Whitespace
        assertThrows(IllegalArgumentException.class,
                () -> PopulationDistributionService
                        .getUrbanAndRuralPopulationByCountry("   "));
    }

    /**
     * Test population distribution calculations for valid data
     */
    @Test
    void testValidPopulationCalculation() {

        String result = PopulationDistributionService
                .formatPopulationDistributionTable(
                        MOCK_DATA.subList(0, 3)
                );

        // United Kingdom: 80% city, 20% non-city
        assertTrue(result.contains("80.00%"));
        assertTrue(result.contains("20.00%"));

        // France: 60% city, 40% non-city
        assertTrue(result.contains("60.00%"));
        assertTrue(result.contains("40.00%"));

        // Germany: 75% city, 25% non-city
        assertTrue(result.contains("75.00%"));
        assertTrue(result.contains("25.00%"));
    }

    // TEST INVALID MOCK DATASET ARE HANDLED CORRECTLY

    /**
     * Test city if it exceeds total population
     */
    @Test
    void testCityExceedsTotal() {
        // "City Exceeds Total", 1000000L, 1200000L
        String result = PopulationDistributionService
                .formatPopulationDistributionTable(
                        List.of(INVALID_MOCK_DATA.get(0))
                );

        String expected = String.format(
                "%-40.40s | %18s | %18s | %12s | %12s",
                "City Exceeds Total",
                "N/A", // City Population
                "N/A", // Non-City Population
                "N/A", // City%
                "N/A" // Non-City%
        );

        assertTrue(result.contains(expected));
    }


    /**
     * Test if total population is negative
     */
    @Test
    void testNegativeTotal() {

        // "Negative Total", -500000L, 200000L
        String result = PopulationDistributionService
                .formatPopulationDistributionTable(
                        List.of(INVALID_MOCK_DATA.get(1))
                );

        String expected = String.format(
                "%-40.40s | %18s | %18s | %12s | %12s",
                "Negative Total",
                "N/A", // City Population
                "N/A", // Non-City Population
                "N/A", // City%
                "N/A" // Non-City%
        );

        assertTrue(result.contains(expected));
    }

    /**
     * Test if city is negative value
     */
    @Test
    void testNegativeCity() {

        // "Negative City", 1000000L, -200000L
        String result = PopulationDistributionService
                .formatPopulationDistributionTable(
                        List.of(INVALID_MOCK_DATA.get(2))
                );

        String expected = String.format(
                "%-40.40s | %18s | %18s | %12s | %12s",
                "Negative City",
                "N/A", // City Population
                "N/A", // Non-City Population
                "N/A", // City%
                "N/A" // Non-City%
        );

        assertTrue(result.contains(expected));
    }

    /**
     * Test if total population is zero and valid city population
     */
    @Test
    void testZeroPopulation() {

        // "Zero Total", 0L, 1000L
        String result = PopulationDistributionService
                .formatPopulationDistributionTable(
                        List.of(INVALID_MOCK_DATA.get(3))
                );

        String expected = String.format(
                "%-40.40s | %18s | %18s | %12s | %12s",
                "Zero Total",
                "N/A", // City Population
                "N/A", // Non-City Population
                "N/A", // City%
                "N/A" // Non-City%
        );

        assertTrue(result.contains(expected));
    }


    // TEST VALID MOCK DATASET

    /**
     * Test without city population
     */
    @Test
    void testNoCityPopulation() {

        String result = PopulationDistributionService
                .formatPopulationDistributionTable(
                        List.of(MOCK_DATA.get(5))
                );


        String expected = String.format(
                "%-40.40s | %18s | %18s | %12s | %12s",
                "No City Population",
                "0", // City Population
                "20,000,000", // Non-City Population
                " 0.00%", // City%
                "100.00%" // Non-City%
        );

        assertTrue(result.contains(expected));
    }

    /**
     * Test Empty population data
     */
    @Test
    void testEmptyPopulationData() {
        String result = PopulationDistributionService
                .formatPopulationDistributionTable(List.of());

        assertEquals("No data available.", result);
    }
}