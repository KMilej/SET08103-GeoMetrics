package com.napier.sem.services;

import org.junit.jupiter.api.Test;

import com.napier.sem.models.Continent;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

@EnabledIfEnvironmentVariable(
        named = "RUN_CAPITAL_CITY_INTEGRATION_TESTS",
        matches = "true"
)

class CapitalCityReportServiceTest {

    // 1. Test all capital cities in the world
    /**
     * Tests that the report for the top 10 capital cities worldwide
     * is generated with the expected title and column headings.
     */
    @Test
    void testPrintAllCapitalCitiesByPopulation() throws SQLException {

        // Act
        String report =
                CapitalCityReportService.printAllCapitalCitiesByPopulation();

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("All Capital Cities by Population"));
        assertTrue(report.contains("Name"));
        assertTrue(report.contains("Country"));
        assertTrue(report.contains("Population"));
    }

    // 2. Test all capital cities in a continent
    /**
     * Tests that the report for the top 10 capital cities in Europe
     * is generated with the correct continent and column headings.
     */
    @Test
    void testPrintCapitalCitiesByContinentByPopulation() throws SQLException {

        // Act
        String report =
                CapitalCityReportService.printCapitalCitiesByContinentByPopulation(
                        Continent.EUROPE
                );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("Europe"));
        assertTrue(report.contains("Name"));
        assertTrue(report.contains("Country"));
        assertTrue(report.contains("Population"));
    }

    // 3. Test all capital cities in a region
    /**
     * Tests that the report for the top 10 capital cities in Western Europe region
     * is generated with the correct region and column headings.
     */
    @Test
    void testPrintCapitalCitiesByRegionByPopulation() throws SQLException {

        // Act
        String report =
                CapitalCityReportService.printCapitalCitiesByRegionByPopulation(
                        "Western Europe"
                );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("Western Europe"));
        assertTrue(report.contains("Name"));
        assertTrue(report.contains("Country"));
        assertTrue(report.contains("Population"));
    }

    // 4. Test top N capital cities in the world
    /**
     * Tests that the report for the top 10 capital cities in the worlds
     * is generated with the expected title and column headings.
     */
    @Test
    void testPrintTopNCapitalCitiesInWorld() throws SQLException {

        // Act
        String report =
                CapitalCityReportService.printTopNCapitalCitiesInWorld(10);

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("Top 10"));
        assertTrue(report.contains("Name"));
        assertTrue(report.contains("Country"));
        assertTrue(report.contains("Population"));
    }

    // 5. Test top N capital cities in a continent
    /**
     * Tests that the report for the top 10 capital cities in Europe
     * is generated with the correct continent and column headings.
     */
    @Test
    void testPrintTopNCapitalCitiesByContinent() throws SQLException {

        // Act
        String report =
                CapitalCityReportService.printTopNCapitalCitiesByContinent(
                        10, Continent.EUROPE
                );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("Top 10"));
        assertTrue(report.toLowerCase().contains("europe"));
        assertTrue(report.contains("Name"));
        assertTrue(report.contains("Country"));
        assertTrue(report.contains("Population"));
    }

    // 6. Test top N capital cities in a region
    /**
     * Tests that the report for the top 10 capital cities in Western Europe
     * is generated with the correct region and column headings.
     */
    @Test
    void testPrintTopNCapitalCitiesByRegion() throws SQLException {

        // Act
        String report =
                CapitalCityReportService.printTopNCapitalCitiesByRegion(
                        10, "Western Europe"
                );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("Top 10"));
        assertTrue(report.contains("Western Europe"));
        assertTrue(report.contains("Name"));
        assertTrue(report.contains("Country"));
        assertTrue(report.contains("Population"));
    }
}

