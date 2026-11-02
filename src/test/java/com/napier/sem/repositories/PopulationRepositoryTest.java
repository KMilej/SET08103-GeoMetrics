package com.napier.sem.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

import com.napier.sem.repositories.PopulationRepository.PopulationDistributionData;
import com.napier.sem.services.PopulationDistributionService;
import com.napier.sem.models.Continent;

class PopulationDistributionRepositoryTest {

    // Expected population data result shared across the test
    private static final List<PopulationDistributionData> EXPECTED = List.of(
            new PopulationDistributionData("Germany", 82164700L, 26245483L),
            new PopulationDistributionData("France", 59225700L, 9244494L)
    );

    private final PopulationRepository repository =
            new PopulationRepository();

    /**
     * Test null scope
     */
    @Test
    void testNullScope(){
        assertThrows(IllegalArgumentException.class,
                ()-> repository.getPopulationDistributionData(
                        null, "FRA"
                ));
    }

    /**
     * Test invalid scope
     */
    @Test
    void testInvalidScope(){
        assertThrows(IllegalArgumentException.class,
                ()-> repository.getPopulationDistributionData(
                        "XYZ", "FRA"
                ));
    }

    /**
     * Test unknown country code
     * @throws SQLException
     */
    @Test
    void testUnknownCountryCode() throws SQLException{
        String result = PopulationDistributionService
                .getUrbanAndRuralPopulationByCountry("ABC");

        assertEquals("No data available.", result);
    }

    /**
     * Test unknown region
     * @throws SQLException
     */
    @Test
    void testUnknownRegion() throws SQLException{

        String result = PopulationDistributionService
                .getUrbanAndRuralPopulationByRegion("EFG");

        assertEquals("No data available.", result);
    }

    /**
     * Test unknown Continent
     * @throws SQLException
     */
    @Test
    void testUnknownContinent() throws SQLException{
        assertThrows(IllegalArgumentException.class,
                () -> Continent.valueOf("XYZ"));
    }

    /**
     * Test unknown region without records
     */
    @Test
    void testUnkownRegionNoRecord() throws SQLException{
        List<PopulationDistributionData> result =
                repository.getPopulationDistributionData(
                        "REGION", "UNKNOWN");

        // Result should be empty
        assertTrue(result.isEmpty());
    }

    /**
     * Test unknown country without records
     */
    @Test
    void testUnkownCountryNoRecord() throws SQLException{
        List<PopulationDistributionData> result =
                repository.getPopulationDistributionData(
                        "COUNTRY", "UNKNOWN");

        // Result should be empty
        assertTrue(result.isEmpty());
    }

    /**
     * Test order of result if listed from largest to smallest
     */
    @Test
    void testResultOrdering() throws SQLException{
        List<PopulationDistributionData> result =
                repository.getPopulationDistributionData(
                        "CONTINENT", "Europe");

        // Result should not be empty
        assertFalse(result.isEmpty());

        // Result should be listed from largest to smallest total population
        for(int i = 0; i < result.size() - 1; i++){
            assertTrue(
                    result.get(i).totalPopulation() >=
                            result.get(i + 1).totalPopulation()
            );
        }
    }

    /**
     * Test SQL query retrieves country population distribution data correctly
     * @throws SQLException
     */
    @Test
    void testCountryPopulationDistribution() throws SQLException {
        List<PopulationDistributionData> country =
                repository.getPopulationDistributionData(
                        "COUNTRY", "FRA");

        // Test if result is not empty
        assertFalse(country.isEmpty());

        // Check if it contains expected population record
        // EXPECTED: "France", 59225700L, 9244494L
        assertTrue(country.contains(EXPECTED.get(1)),
                "Country should include expected data population record");
    }

    /**
     * Test SQL query retrieves region population distribution data correctly
     * @throws SQLException
     */
    @Test
    void testRegionPopulationDistribution() throws SQLException {

        List<PopulationDistributionData> region =
                repository.getPopulationDistributionData(
                        "REGION", "Western Europe");

        // Test if result is not empty
        assertFalse(region.isEmpty());

        // Check if it contains all expected region population records
        // EXPECTED: "Germany", 82164700L, 26245483L
        // EXPECTED: "France", 59225700L, 9244494L
        assertTrue(region.containsAll(EXPECTED),
                "Region should include all expected test data population records");
    }

    /**
     * Test SQL query retrieves continent population distribution data correctly
     * @throws SQLException
     */
    @Test
    void testContinentPopulationDistribution() throws SQLException {

        List<PopulationDistributionData> continent =
                repository.getPopulationDistributionData(
                        "CONTINENT", "Europe");

        // Check if result is not empty
        assertFalse(continent.isEmpty());

        // Check if it contains all expected continent population records
        // EXPECTED: "Germany", 82164700L, 26245483L
        // EXPECTED: "France", 59225700L, 9244494L
        assertTrue(continent.containsAll(EXPECTED),
                "Continent should include all expected test data population records");
    }
}