/****************************************************************
 * Project: GeoMetrics
 * File:    CityReportService.java
 * Author:  Kamil Milej
 ***************************************************************/

package com.napier.sem.services;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.repositories.CityReportRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Creates text reports for UC02 cities, ordered by population.
 * Checks the input before requesting data from the repository.
 */
public class CityReportService {

    /**
     * Creates a report of all cities in the world.
     *
     * @return the report text, or a message if no cities are found
     * @throws SQLException if the database query fails
     */
    public static String getAllCitiesByPopulation() throws SQLException {
        CityReportRepository repository = new CityReportRepository();
        List<City> cities = repository.getAllCitiesByPopulation();

        if (cities.isEmpty()) {
            return "No cities found.";
        }

        StringBuilder report = new StringBuilder();
        report.append(String.format(
                "%-35s %-12s %-25s %12s%n",
                "City", "Country Code", "District", "Population"));

        for (City city : cities) {
            report.append(String.format(
                    "%-35s %-12s %-25s %12d%n",
                    city.getName(),
                    city.getCountryCode(),
                    city.getDistrict(),
                    city.getPopulation()));
        }

        return report.toString();
    }

    /**
     * Creates a city report for a continent.
     *
     * @param continent the selected continent
     * @return the report text, or a message if no cities are found
     * @throws SQLException if the database query fails
     * @throws IllegalArgumentException if the continent is null
     */
    public static String getCitiesByContinentByPopulation(Continent continent) throws SQLException {
        if (continent == null) {
            throw new IllegalArgumentException("Continent cannot be null.");
        }
        CityReportRepository repository = new CityReportRepository();
        List<City> cities = repository.getCitiesByContinentByPopulation(continent);
        return formatCityReport(cities);
    }

    /**
     * Creates a city report for a region.
     *
     * @param region the region name, for example Western Europe
     * @return the report text, or a message if no cities are found
     * @throws SQLException if the database query fails
     * @throws IllegalArgumentException if the region is null or blank
     */
    public static String getCitiesByRegionByPopulation(String region) throws SQLException {
        checkLocation(region, "Region");
        CityReportRepository repository = new CityReportRepository();
        List<City> cities = repository.getCitiesByRegionByPopulation(region);
        return formatCityReport(cities);
    }

    /**
     * Creates a city report for a country.
     *
     * @param countryCode the country code, for example GBR
     * @return the report text, or a message if no cities are found
     * @throws SQLException if the database query fails
     * @throws IllegalArgumentException if the country code is null or blank
     */
    public static String getCitiesByCountryByPopulation(String countryCode) throws SQLException {
        checkLocation(countryCode, "Country code");
        CityReportRepository repository = new CityReportRepository();
        List<City> cities = repository.getCitiesByCountryByPopulation(countryCode);
        return formatCityReport(cities);
    }

    /**
     * Creates a city report for a district.
     *
     * @param district the district name, for example Scotland
     * @return the report text, or a message if no cities are found
     * @throws SQLException if the database query fails
     * @throws IllegalArgumentException if the district is null or blank
     */
    public static String getCitiesByDistrictByPopulation(String district) throws SQLException {
        checkLocation(district, "District");
        CityReportRepository repository = new CityReportRepository();
        List<City> cities = repository.getCitiesByDistrictByPopulation(district);
        return formatCityReport(cities);
    }

    /**
     * Creates a report of the top N cities in the world.
     *
     * @param topN the maximum number of cities to show
     * @return the report text, or a message if no cities are found
     * @throws SQLException if the database query fails
     * @throws IllegalArgumentException if N is zero or negative
     */
    public static String getTopNCitiesInWorld(int topN) throws SQLException {
        checkTopN(topN);
        CityReportRepository repository = new CityReportRepository();
        List<City> cities = repository.getTopNCitiesInWorld(topN);
        return formatCityReport(cities);
    }

    /**
     * Creates a report of the top N cities in a continent.
     *
     * @param topN the maximum number of cities to show
     * @param continent the selected continent
     * @return the report text, or a message if no cities are found
     * @throws SQLException if the database query fails
     * @throws IllegalArgumentException if N is not positive or the continent is null
     */
    public static String getTopNCitiesByContinent(int topN, Continent continent) throws SQLException {
        checkTopN(topN);
        if (continent == null) {
            throw new IllegalArgumentException("Continent cannot be null.");
        }
        CityReportRepository repository = new CityReportRepository();
        List<City> cities = repository.getTopNCitiesByContinent(topN, continent);
        return formatCityReport(cities);
    }

    public static String getTopNCitiesByRegion(int topN, String region) throws SQLException {
        // TODO: Build the report for the top N cities in the selected region.
        return "";
    }

    public static String getTopNCitiesByCountry(int topN, String countryCode) throws SQLException {
        // TODO: Build the report for the top N cities in the selected country.
        return "";
    }

    public static String getTopNCitiesByDistrict(int topN, String district) throws SQLException {
        // TODO: Build the report for the top N cities in the selected district.
        return "";
    }

    /**
     * Checks that N is positive.
     *
     * @param topN the requested number of cities
     * @throws IllegalArgumentException if N is zero or negative
     */
    private static void checkTopN(int topN) {
        if (topN <= 0) {
            throw new IllegalArgumentException("N must be a positive whole number.");
        }
    }

    /**
     * Checks that a location has been provided.
     *
     * @param location the location to check
     * @param name the field name used in the error message
     * @throws IllegalArgumentException if the location is null or blank
     */
    private static void checkLocation(String location, String name) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be empty.");
        }
    }

    /**
     * Formats cities as a table with a header and one row per city.
     *
     * @param cities the cities to include in their current order
     * @return the table text, or a message if the list is empty
     */
    static String formatCityReport(List<City> cities) {
        if (cities.isEmpty()) {
            return "No cities found.";
        }

        StringBuilder report = new StringBuilder();
        report.append(String.format(
                "%-35s %-12s %-25s %12s%n",
                "City", "Country Code", "District", "Population"));

        for (City city : cities) {
            report.append(String.format(
                    "%-35s %-12s %-25s %12d%n",
                    city.getName(), city.getCountryCode(),
                    city.getDistrict(), city.getPopulation()));
        }
        return report.toString();
    }
}
