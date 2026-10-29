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

        assertTrue(report.contains("Country Code"));
        assertTrue(report.contains("Mumbai (Bombay)"));
        assertTrue(report.contains("10500000"));
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
     * Checks the message returned when no cities match the region.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_CITY_INTEGRATION_TESTS", matches = "true")
    void getCitiesByRegionByPopulation_reportsNoCitiesForUnknownRegion() throws SQLException {
        String report = CityReportService.getCitiesByRegionByPopulation("Unknown region");

        assertEquals("No cities found.", report);
    }

    /**
     * Checks that empty and whitespace-only country codes are rejected.
     */
    @Test
    void getCitiesByCountryByPopulation_rejectsEmptyCountryCode() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByCountryByPopulation(""));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getCitiesByCountryByPopulation("   "));
    }

    /**
     * Checks that null and whitespace-only districts are rejected.
     */
    @Test
    void getCitiesByDistrictByPopulation_rejectsMissingDistrict() {
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
     * Checks that a null continent and zero N are rejected.
     */
    @Test
    void getTopNCitiesByContinent_rejectsInvalidParameters() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByContinent(2, null));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByContinent(0, Continent.EUROPE));
    }

    /**
     * Checks that null and empty regions are rejected.
     */
    @Test
    void getTopNCitiesByRegion_rejectsMissingRegion() {
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByRegion(2, null));
        assertThrows(IllegalArgumentException.class,
                () -> CityReportService.getTopNCitiesByRegion(2, ""));
    }




}
