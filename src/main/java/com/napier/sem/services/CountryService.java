package com.napier.sem.services;

import com.napier.sem.models.Continent;
import com.napier.sem.models.Country;
import com.napier.sem.repositories.CountryRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Sits between the rest of the app and the country database queries.
 * Grabs the country data from the repository and gets it ready to show,
 * including building the nicely lined-up population report.
 */
public class CountryService {

    // Repository we use to pull country data out of the database
    private static final CountryRepository countryRepository = new CountryRepository();


    /**
     * Gets every country in the database, biggest population first.
     *
     * @return a list of all countries, sorted by population (highest to lowest)
     * @throws SQLException if something goes wrong talking to the database
     */
    public List<Country> getAllCountriesReports() throws SQLException {

        return countryRepository.getAllCountries();
    }

    /**
     * Builds a text report of all countries, biggest population first.
     * It comes out as a table with a header row, a divider line,
     * and one row per country (code, name, continent, region, population, capital).
     *
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printAllCountriesByPopulation() {
        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());
        // Report title
        sb.append("               ----- All the countries in the world organised by largest population to smallest.-----").append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %13s | %s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Divider line under the header
        sb.append("-".repeat(135)).append(System.lineSeparator());
        try {
            List<Country> countries = countryRepository.getAllCountries();

            // One row per country (population gets commas, 1,234,567)
            for (Country country : countries) {
                sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %,13d | %s%n",
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalName()
                ));

            }
        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

        return sb.toString();
    }


    /**
     * Gets every country in the database, biggest population first.
     *
     * @return a list of all countries, sorted by population (highest to lowest)
     * @throws SQLException if something goes wrong talking to the database
     */
    public List<Country> getAllCountriesByContinent() throws SQLException {

        return countryRepository.getAllCountries();
    }


    /**
     * Builds a text report of all countries in one continent, biggest population first.
     * It comes out as a table with a header row, a divider line,
     * and one row per country (code, name, continent, region, population, capital).
     *
     * @param continent the continent to report on
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printCountriesByContinentByPopulation(Continent continent) {
        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());
        // Report title
        sb.append("               ----- All the countries in a continent organised by largest population to smallest.-----").append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %13s | %s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Divider line under the header
        sb.append("-".repeat(135)).append(System.lineSeparator());
        try {
            List<Country> countries = countryRepository.getAllCountriesByContinent(continent);

            // One row per country (population gets commas, 1,234,567)
            for (Country country : countries) {
                sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %,13d | %s%n",
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalName()
                ));

            }
        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

        return sb.toString();
    }


    /**
     * Builds a text report of all countries in one region, biggest population first.
     * It comes out as a table with a header row, a divider line,
     * and one row per country (code, name, continent, region, population, capital).
     *
     * @param region the region to report on (e.g. "Western Europe")
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printCountriesByRegionByPopulation(String region) {
        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());
        // Report title
        sb.append("               ----- All the countries in a region organised by largest population to smallest.-----").append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %13s | %s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Divider line under the header
        sb.append("-".repeat(135)).append(System.lineSeparator());
        try {
            List<Country> countries = countryRepository.getAllCountriesByRegion(region);

            // One row per country (population gets commas, 1,234,567)
            for (Country country : countries) {
                sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %,13d | %s%n",
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalName()
                ));

            }
        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

        return sb.toString();
    }


    /**
     * Builds a text report of the top N most populated countries in the world, biggest first.
     * It comes out as a table with a header row, a divider line,
     * and one row per country (code, name, continent, region, population, capital).
     *
     * @param topN how many countries to show (e.g. 10 for the top ten)
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printTopNCountriesInWorld(int topN) {
        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());
        // Report title
        sb.append("               ----- The top N populated countries in the world where N is provided by the user.-----").append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %13s | %s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Divider line under the header
        sb.append("-".repeat(135)).append(System.lineSeparator());
        try {
            List<Country> countries = countryRepository.getTopNCountriesInWorld(topN);

            // One row per country (population gets commas, 1,234,567)
            for (Country country : countries) {
                sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %,13d | %s%n",
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalName()
                ));

            }
        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

        return sb.toString();
    }


    /**
     * Builds a text report of the top N most populated countries in one continent,
     * biggest population first.
     * It comes out as a table with a header row, a divider line,
     * and one row per country (code, name, continent, region, population, capital).
     *
     * @param topN      how many countries to show (e.g. 10 for the top ten)
     * @param continent the continent to report on
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printTopNCountriesByContinent(int topN, Continent continent) {
        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());
        // Report title
        sb.append("               ----- The top N populated countries in a continent where N is provided by the user.-----").append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %13s | %s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Divider line under the header
        sb.append("-".repeat(135)).append(System.lineSeparator());
        try {
            List<Country> countries = countryRepository.getTopNCountriesInWorldByContinent(topN, continent);

            // One row per country (population gets commas, 1,234,567)
            for (Country country : countries) {
                sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %,13d | %s%n",
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalName()
                ));
            }
        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

        return sb.toString();
    }


    /**
     * Builds a text report of the top N most populated countries in one region, biggest first.
     * It comes out as a table with a header row, a divider line,
     * and one row per country (code, name, continent, region, population, capital).
     *
     * @param topN   how many countries to show (e.g. 10 for the top ten)
     * @param region the region to report on (e.g. "Western Europe")
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printTopNCountriesByRegion(int topN, String region) {
        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());
        // Report title
        sb.append("               ----- The top N populated countries in a region where N is provided by the user.-----").append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %13s | %s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Divider line under the header
        sb.append("-".repeat(135)).append(System.lineSeparator());
        try {
            List<Country> countries = countryRepository.getTopNCountriesInWorldByRegion(topN, region);

            // One row per country (population gets commas, 1,234,567)
            for (Country country : countries) {
                sb.append(String.format("%-5s | %-45s | %-15s | %-26s | %,13d | %s%n",
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalName()
                ));

            }
        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

        return sb.toString();
    }


}