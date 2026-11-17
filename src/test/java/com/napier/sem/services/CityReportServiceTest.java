/****************************************************************
 * Project: GeoMetrics
 * File:    CityReportServiceTest.java
 * Author:  Kamil Milej
 ***************************************************************/

package com.napier.sem.services;

import com.napier.sem.models.Continent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests UC02 input checks and report text.
 * Tests that need MySQL run when RUN_CITY_INTEGRATION_TESTS is true.
 */
class CityReportServiceTest {

    /**
     * Checks that the report includes a country header, a city and its population.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_CITY_INTEGRATION_TESTS", matches = "true")
    void getAllCitiesByPopulation_formatsReportWithCityData() throws SQLException {
        String report = CityReportService.getAllCitiesByPopulation();

        assertTrue(report.contains("Country Name"));
        assertTrue(report.contains("Mumbai (Bombay)"));
        assertTrue(report.contains("10500000"));
    }

    /**
     * Checks that a top one report includes London but not Birmingham.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_CITY_INTEGRATION_TESTS", matches = "true")
    void getTopNCitiesByCountry_printsOnlyRequestedCity() throws SQLException {
        String report = CityReportService.getTopNCitiesByCountry(1, "United Kingdom");

        assertTrue(report.contains("London"));
        assertTrue(report.contains("7285000"));
        assertFalse(report.contains("Birmingham"));
    }


    /**
     * Checks that a null continent is rejected.
     */
    @Test
    void getCitiesByContinentByPopulation_rejectsNullContinent() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByContinentByPopulation(null));
    }

    /**
     * Checks that empty, null and whitespace-only country names are rejected.
     */
    @Test
    void getCitiesByCountryByPopulation_rejectsInvalidCountryName() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByCountryByPopulation(""));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByCountryByPopulation("   "));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByCountryByPopulation(null));
    }

    /**
     * Checks that empty, null and whitespace-only districts are rejected.
     */
    @Test
    void getCitiesByDistrictByPopulation_rejectsInvalidDistrict() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByDistrictByPopulation(""));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByDistrictByPopulation(null));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByDistrictByPopulation("   "));
    }

    /**
     * Checks that zero and negative values of N are rejected.
     */
    @Test
    void getTopNCitiesInWorld_rejectsZeroAndNegativeN() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesInWorld(0));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesInWorld(-1));
    }

    /**
     * Checks that a null continent is rejected
     */
    @Test
    void getTopNCitiesByContinent_rejectsInvalidParameters() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByContinent(2, null));
    }

    /**
     * Checks that null, whitespace and empty country are rejected.
     *
     */
    @Test
    void getTopNCitiesByCountry_rejectsInvalidParameters() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByCountry(2, null));

        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByCountry(2, "    "));

        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByCountry(2, ""));
    }


    /**
     * Checks that null, whitespace and empty regions are rejected.
     */
    @Test
    void getTopNCitiesByRegion_rejectsMissingRegion() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByRegion(2, null));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByRegion(2, ""));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByRegion(2, "   "));
    }

    /**
     * Checks that a top N but null, whitespace-only and a blank district are rejected.
     */
    @Test
    void getTopNCitiesByDistrict_rejectsNullAndBlankDistrict() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByDistrict(2, null));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByDistrict(2, "   "));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByDistrict(2, ""));
    }

    /**
     * Checks that null, whitespace-only and blank regions are rejected.
     */
    @Test
    void getCitiesByRegionByPopulation_rejectsInvalidRegion() {

        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByRegionByPopulation(null));

        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByRegionByPopulation("   "));

        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByRegionByPopulation(""));
    }

    /**
     * Check for zero or negative top N cities by District
     */
    @Test
    void getTopNCitiesByDistrict_invalidTopN(){

        // Zero Top N
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByDistrict(
                        0, "Scotland"));

        // Negative Top N
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByDistrict(
                        -1, "Scotland"));
    }

    /**
     * Check for zero or negative top N cities by Country
     */
    @Test
    void getTopNCitiesByCountry_invalidTopN(){

        // Zero Top N
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByCountry(
                        0, "United Kingdom"));

        // Negative Top N
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByCountry(
                        -1, "United Kingdom"));
    }

    /**
     * Check for zero or negative top N Cities Region
     */
    @Test
    void getTopNCitiesByRegion_invalidTopN(){

        // Zero Top N
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByRegion(
                        0, "Western Europe"));

        // Negative Top N
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByRegion(
                        -1, "Western Europe"));
    }

    /**
     * Check for zero or negative top N Cities Continent
     */
    @Test
    void getTopNCitiesByContinent_invalidTopN(){

        // Zero Top N
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByContinent(
                        0, Continent.EUROPE));

        // Negative Top N
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByContinent(
                        -1, Continent.EUROPE));
    }

    /**
     * Checks that Top N greater than the available cities
     * returns only all existing cities
     */
    @Test
    @EnabledIfEnvironmentVariable(
            named = "RUN_CITY_INTEGRATION_TESTS",
            matches = "true"
    )
    void getTopNCitiesByCountry_exceedsAvailableCities()
            throws SQLException {

        String allCities =
                CityReportService.getCitiesByCountryByPopulation(
                        "United Kingdom");

        String topCities =
                CityReportService.getTopNCitiesByCountry(
                        99999999, "United Kingdom");

        assertEquals(allCities, topCities);
    }

    /**
     * Checks that non-existing country returns no cities
     *
     * @throws SQLException if the database query fails
     *
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_CITY_INTEGRATION_TESTS", matches = "true")
    void testUnknownCountryNames()
            throws SQLException {

        String resultTopN = CityReportService.getTopNCitiesByCountry(
                2, "Unknown");
        String result = CityReportService.getCitiesByCountryByPopulation(
                "Unknown");

        assertEquals("No cities found.", resultTopN);
        assertEquals("No cities found.", result);
    }

    /**
     * Checks that non-existing district returns no cities
     *
     * @throws SQLException if the database query fails
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_CITY_INTEGRATION_TESTS", matches = "true")
    void testUnknownDistrictNames()
            throws SQLException {

        String resultTopN = CityReportService.getTopNCitiesByDistrict(
                2, "Unknown");
        String result = CityReportService.getCitiesByDistrictByPopulation(
                "Unknown");

        assertEquals("No cities found.", resultTopN);
        assertEquals("No cities found.", result);
    }

    /**
     * Checks that non-existing region returns no cities
     *
     * @throws SQLException if the database query fails
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_CITY_INTEGRATION_TESTS", matches = "true")
    void testUnknownRegionNames()
            throws SQLException {

        String resultTopN = CityReportService.getTopNCitiesByRegion(
                2, "Unknown");
        String result = CityReportService.getCitiesByRegionByPopulation(
                "Unknown");

        assertEquals("No cities found.", resultTopN);
        assertEquals("No cities found.", result);

    }
}
