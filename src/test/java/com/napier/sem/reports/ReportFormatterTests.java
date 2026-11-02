
package com.napier.sem.reports;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportFormatterTest {

    // Creates sample capital cities for testing
    private List<City> createCapitalCities() {

        List<City> cities = new ArrayList<>();

        City london = new City(1, "London", "GBR", "England", 7285000);
        london.setCountryName("United Kingdom");

        City paris = new City(2, "Paris", "FRA", "Ile-de-France", 2125246);
        paris.setCountryName("France");

        cities.add(london);
        cities.add(paris);

        return cities;
    }

    // 1. All capital cities worldwide
    @Test
    void testPrintAllCapitalCities() {

        // Arrange
        List<City> cities = createCapitalCities();

        // Act
        String report = ReportFormatter.printAllCapitalCities(
                "All Capital Cities by Population", cities
        );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("All Capital Cities by Population"));
        assertTrue(report.contains("London"));
        assertTrue(report.contains("Paris"));
    }

    // 2. All capital cities by continent
    @Test
    void testPrintAllCapitalCitiesByContinent() {

        // Arrange
        List<City> cities = createCapitalCities();

        // Act
        String report = ReportFormatter.printAllCapitalCitiesByContinent(
                Continent.EUROPE, cities
        );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("Europe"));
        assertTrue(report.contains("London"));
        assertTrue(report.contains("Paris"));
    }

    // 3. All capital cities by region
    @Test
    void testPrintAllCapitalCitiesByRegion() {

        // Arrange
        List<City> cities = createCapitalCities();

        // Act
        String report = ReportFormatter.printAllCapitalCitiesByRegion(
                "Western Europe", cities
        );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("Western Europe"));
        assertTrue(report.contains("London"));
    }

    // 4. Top N capital cities worldwide
    @Test
    void testPrintTopNCapitalCities() {

        // Arrange
        List<City> cities = createCapitalCities();

        // Act
        String report = ReportFormatter.printTopNCapitalCities(2, cities);

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("top 2"));
        assertTrue(report.contains("London"));
        assertTrue(report.contains("Paris"));
    }

    // 5. Top N capital cities by continent
    @Test
    void testPrintTopNCapitalCitiesByContinent() {

        // Arrange
        List<City> cities = createCapitalCities();

        // Act
        String report = ReportFormatter.printTopNCapitalCitiesByContinent(
                2, Continent.EUROPE, cities
        );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("top 2"));
        assertTrue(report.contains("Europe"));
        assertTrue(report.contains("London"));
    }

    // 6. Top N capital cities by region
    @Test
    void testPrintTopNCapitalCitiesByRegion() {

        // Arrange
        List<City> cities = createCapitalCities();

        // Act
        String report = ReportFormatter.printTopNCapitalCitiesByRegion(
                2, "Western Europe", cities
        );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("top 2"));
        assertTrue(report.contains("Western Europe"));
        assertTrue(report.contains("Paris"));
    }

    // 7. Check headings and full country names
    @Test
    void testReportColumnsAndCountryNames() {

        // Arrange
        List<City> cities = createCapitalCities();

        // Act
        String report = ReportFormatter.printAllCapitalCities(
                "Capital Cities", cities
        );

        // Assert
        assertTrue(report.contains("Name"));
        assertTrue(report.contains("Country"));
        assertTrue(report.contains("Population"));

        assertTrue(report.contains("United Kingdom"));
        assertTrue(report.contains("France"));
        assertTrue(report.contains("7,285,000"));
        assertTrue(report.contains("2,125,246"));
    }

    // 8. Check an empty list still produces a report
    @Test
    void testPrintAllCapitalCitiesWithEmptyList() {

        // Arrange
        List<City> cities = new ArrayList<>();

        // Act
        String report = ReportFormatter.printAllCapitalCities(
                "Empty Capital Cities Report", cities
        );

        // Assert
        assertNotNull(report);
        assertTrue(report.contains("Empty Capital Cities Report"));
        assertTrue(report.contains("Name"));
        assertTrue(report.contains("Country"));
        assertTrue(report.contains("Population"));
        assertFalse(report.contains("London"));
    }
}
