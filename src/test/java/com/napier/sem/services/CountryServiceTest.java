package com.napier.sem.services;

import com.napier.sem.models.Continent;
import com.napier.sem.models.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the country reports.
 * Input checks run every time. Tests that need MySQL with the world
 * database only run when RUN_COUNTRY_INTEGRATION_TESTS is true.
 */
class CountryServiceTest {

    // ---------- Input checks (no database needed) ----------

    /**
     * Checks that zero and negative values of N are rejected for a continent report.
     */
    @Test
    void printTopNCountriesByContinent_rejectsZeroAndNegativeN() {
        assertThrows(IllegalArgumentException.class,
                () -> CountryService.printTopNCountriesByContinent(0, Continent.EUROPE));
        assertThrows(IllegalArgumentException.class,
                () -> CountryService.printTopNCountriesByContinent(-1, Continent.EUROPE));
    }

    /**
     * Checks that zero and negative values of N are rejected for a region report.
     */
    @Test
    void printTopNCountriesByRegion_rejectsZeroAndNegativeN() {
        assertThrows(IllegalArgumentException.class,
                () -> CountryService.printTopNCountriesByRegion(0, "Western Europe"));
        assertThrows(IllegalArgumentException.class,
                () -> CountryService.printTopNCountriesByRegion(-1, "Western Europe"));
    }

    // ---------- Report text (needs MySQL) ----------

    /**
     * Checks that the list of all countries is returned, biggest population first.
     *
     * @throws SQLException if the test cannot read the database
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
    void getAllCountriesReports_returnsCountriesBiggestFirst() throws SQLException {
        List<Country> countries = new CountryService().getAllCountriesReports();

        assertEquals(239, countries.size());
        assertEquals("CHN", countries.get(0).getCode());
        assertEquals("China", countries.get(0).getName());
    }

    /**
     * Checks that the report has a title, a header row and a row for every country.
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
    void printAllCountriesByPopulation_formatsReportWithCountryData() {
        String report = CountryService.printAllCountriesByPopulation();

        assertTrue(report.contains("All Countries by Population"));
        assertTrue(report.contains("Code"));
        assertTrue(report.contains("Capital"));
        assertTrue(report.contains("1,277,558,000"));
        assertTrue(report.contains("Peking"));
        // 4 lines of title and header, then one line per country
        assertEquals(239 + 7, report.lines().count());
    }

    /**
     * Checks that a Europe report lists Russia first and leaves out Asian countries.
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
    void printCountriesByContinentByPopulation_printsOnlyRequestedContinent() {
        String report = CountryService.printCountriesByContinentByPopulation(Continent.EUROPE);
        List<String> lines = report.lines().toList();

        assertTrue(report.contains("Countries"));
        assertTrue(report.toLowerCase().contains("europe"));
        assertTrue(lines.stream().anyMatch(
                line -> line.matches("^\\s*1\\s*\\|\\s*RUS\\s*\\|.*")));
        assertTrue(report.contains("Moscow"));
        assertFalse(report.contains("China"));
        assertEquals(46 + 7, lines.size());
    }

    /**
     * Checks that a Western Europe report includes Germany and leaves out Russia.
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
    void printCountriesByRegionByPopulation_printsOnlyRequestedRegion() {
        String report = CountryService.printCountriesByRegionByPopulation("Western Europe");
        List<String> lines = report.lines().toList();

        assertTrue(report.contains("Countries"));
        assertTrue(report.contains("Western Europe"));
        assertTrue(lines.stream().anyMatch(line -> line.matches("^\\s*1\\s*\\|\\s*DEU\\s*\\|.*")));
        assertTrue(report.contains("France"));
        assertFalse(report.contains("Russian Federation"));
    }

    /**
     * Checks the message-free result when no countries match the region:
     * only the title and header are printed.
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
    void printCountriesByRegionByPopulation_unknownRegionGivesHeaderOnly() {
        String report = CountryService.printCountriesByRegionByPopulation("Unknown region");

        assertNotNull(report);
        assertTrue(report.contains("Unknown region"));
        assertTrue(report.contains("Code"));
        assertTrue(report.contains("No countries found."));
    }

    /**
     * Checks that a top three report includes China, India and the United States but not Indonesia.
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
    void printTopNCountriesInWorld_printsOnlyRequestedCountries() {
        String report = CountryService.printTopNCountriesInWorld(3);

        assertTrue(report.contains("Top 3"));
        assertTrue(report.contains("China"));
        assertTrue(report.contains("India"));
        assertTrue(report.contains("United States"));
        assertFalse(report.contains("Indonesia"));
        assertEquals(3 + 7, report.lines().count());
    }

    /**
     * Checks that a top two Europe report includes Russia and Germany but not France.
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
    void printTopNCountriesByContinent_printsOnlyRequestedCountries() {
        String report = CountryService.printTopNCountriesByContinent(2, Continent.EUROPE);

        assertTrue(report.contains(("Top 2")));
        assertTrue(report.toLowerCase().contains("europe"));
        assertTrue(report.contains("Russian Federation"));
        assertTrue(report.contains("Germany"));
        assertFalse(report.contains("France"));
    }

    /**
     * Checks that a top one Western Europe report includes Germany but not France.
     */
    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_COUNTRY_INTEGRATION_TESTS", matches = "true")
    void printTopNCountriesByRegion_printsOnlyRequestedCountry() {
        String report = CountryService.printTopNCountriesByRegion(1, "Western Europe");

        assertTrue(report.contains("Top 1"));
        assertTrue(report.contains("Germany"));
        assertFalse(report.contains("France"));
    }
}