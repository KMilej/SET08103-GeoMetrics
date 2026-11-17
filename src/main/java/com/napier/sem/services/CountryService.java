package com.napier.sem.services;

import com.napier.sem.models.Continent;
import com.napier.sem.models.Country;
import com.napier.sem.reports.ReportFormatter;
import com.napier.sem.repositories.CountryRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Handles country population reports.
 *
 * Retrieves country data from CountryRepository
 * and delegates report presentation to ReportFormatter.
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
     *
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */

    public static String printAllCountriesByPopulation() {
        try {
            List<Country> countries = countryRepository.getAllCountries();
            return ReportFormatter.formatCountries("All Countries by Population", countries);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to build country report", e);
        }
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
     *
     * @param continent the continent to report on
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printCountriesByContinentByPopulation(Continent continent) {

        try {
            List<Country> countries = countryRepository.getAllCountriesByContinent(continent);

            return ReportFormatter.formatCountries(
                    "All Countries in " + continent + " by Population",
                    countries
            );

        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }
    }

    /**
     * Builds a text report of all countries in one region, biggest population first.
     *
     * @param region the region to report on (e.g. "Western Europe")
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printCountriesByRegionByPopulation(String region) {
        try {
            List<Country> countries = countryRepository.getAllCountriesByRegion(region);

            return ReportFormatter.formatCountries(
                    "All Countries in " + region + " by Population",
                    countries
            );
        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

    }

    /**
     * Builds a text report of the top N most populated countries in the world, biggest first.
     *
     * @param topN how many countries to show (e.g. 10 for the top ten)
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printTopNCountriesInWorld(int topN) {

        try {
            List<Country> countries = countryRepository.getTopNCountriesInWorld(topN);

            return ReportFormatter.formatCountries(
                    "Top " + topN + " Countries in the World by Population",
                    countries
            );

        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

    }

    /**
     * Builds a text report of the top N most populated countries in one continent,
     * biggest population first.
     *
     * @param topN      how many countries to show (e.g. 10 for the top ten)
     * @param continent the continent to report on
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printTopNCountriesByContinent(int topN, Continent continent) {

        try {
            List<Country> countries = countryRepository.getTopNCountriesInWorldByContinent(topN, continent);

            return ReportFormatter.formatCountries(
                    "Top " + topN + " Countries in " + continent
                            + " by Population",
                    countries
            );

        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }
    }

    /**
     * Builds a text report of the top N most populated countries in one region, biggest first.
     * @param topN   how many countries to show (e.g. 10 for the top ten)
     * @param region the region to report on (e.g. "Western Europe")
     * @return the whole report as one string, ready to print
     * @throws RuntimeException if the countries can't be loaded from the database
     */
    public static String printTopNCountriesByRegion(int topN, String region) {

        try {
            List<Country> countries = countryRepository.getTopNCountriesInWorldByRegion(topN, region);

            return ReportFormatter.formatCountries(
                    "Top " + topN + " Countries in " + region
                            + " by Population",
                    countries
            );


        } catch (SQLException e) {
            // Wrap it so callers don't have to deal with a checked exception
            throw new RuntimeException("Failed to build country report", e);
        }

    }

}