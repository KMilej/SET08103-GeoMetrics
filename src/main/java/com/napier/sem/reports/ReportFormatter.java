package com.napier.sem.reports;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;

import java.util.List;

public class ReportFormatter {

    // 1. printAllCapitalCities()

    /**
     * Formats a report of all capital cities in the world,
     * ordered by population from largest to smallest.
     *
     * @param title the title displayed at the top of the report
     * @param capitalCities the list of capital cities to format
     * @return a formatted report containing capital city names,
     *         country names and populations
     */

    public static String printAllCapitalCities(String title, List<City> capitalCities) {
        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());

        // Report title
        sb.append("               ----- ")
                .append(title)
                .append(" -----")
                .append(System.lineSeparator());

        // Header row
        sb.append(String.format("%-5s | %-45s | %-45s | %15s%n",
                "No.", "Name", "Country", "Population"));

        // Divider line
        sb.append("-".repeat(120)).append(System.lineSeparator());

        // Number each capital city
        int number = 1;

        for (City city : capitalCities) {
            sb.append(String.format("%-5d | %-45s | %-45s | %,15d%n",
                    number,
                    city.getName(),
                    city.getCountryName(),
                    city.getPopulation()
            ));

            number++;
        }

        return sb.toString();
    }
    // 2. printAllCapitalCitiesByContinent()

    /**
     * Formats a report of all capital cities in a specified continent,
     * ordered by population from largest to smallest.
     *
     * @param continent the continent included in the report
     * @param capitalCities the list of capital cities to format
     * @return a formatted report containing capital city names,
     *         country names and populations
     */


    public static String printAllCapitalCitiesByContinent(
            Continent continent, List<City> capitalCities) {

        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());

        // Report title
        sb.append("               ----- All the capital cities in ")
                .append(continent.toString())
                .append(" organised by largest population to smallest -----")
                .append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-45s | %15s%n",
                "No.", "Name", "Country", "Population"));

        // Divider line under the header
        sb.append("-".repeat(119)).append(System.lineSeparator());

        // Counter for numbering capital cities
        int number = 1;

        // One row per capital city (population gets commas, 1,234,567)
        for (City city : capitalCities) {
            sb.append(String.format("%-5d | %-45s | %-45s | %,15d%n",
                    number,
                    city.getName(),
                    city.getCountryName(),
                    city.getPopulation()
            ));

            number++;
        }

        return sb.toString();
    }


    // 3.printAllCapitalCitiesByRegion()

    /**
     * Formats a report of all capital cities in a specified region,
     * ordered by population from largest to smallest.
     *
     * @param region the region included in the report
     * @param capitalCities the list of capital cities to format
     * @return a formatted report containing capital city names,
     *         country names and populations
     */

    public static String printAllCapitalCitiesByRegion(
            String region, List<City> capitalCities) {

        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());

        // Report title
        sb.append("               ----- All capital cities in ")
                .append(region)
                .append(" organised by largest population to smallest -----")
                .append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-45s | %15s%n",
                "No.", "Name", "Country", "Population"));

        // Divider line under the header
        sb.append("-".repeat(119)).append(System.lineSeparator());

        // Number each capital city
        int number = 1;

        // One row per capital city (population gets commas, 1,234,567)
        for (City city : capitalCities) {
            sb.append(String.format("%-5d | %-45s | %-45s | %,15d%n",
                    number,
                    city.getName(),
                    city.getCountryName(),
                    city.getPopulation()
            ));

            number++;
        }

        return sb.toString();
    }

    // 4. printTopNCapitalCities()

    /**
     * Formats a report of the top N most populated capital cities
     * in the world, ordered by population from largest to smallest.
     *
     * @param topN the number of capital cities requested
     * @param capitalCities the list of capital cities to format
     * @return a formatted report containing the top N capital cities,
     *         their country names and populations
     */


    public static String printTopNCapitalCities(int topN, List<City> capitalCities) {

        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());

        // Report title
        sb.append("               ----- The top ")
                .append(topN)
                .append(" populated capital cities in the world -----")
                .append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-45s | %15s%n",
                "No.", "Name", "Country", "Population"));

        // Divider line under the header
        sb.append("-".repeat(119)).append(System.lineSeparator());

        // Number each capital city
        int number = 1;

        // One row per capital city (population gets commas, 1,234,567)
        for (City city : capitalCities) {
            sb.append(String.format("%-5d | %-45s | %-45s | %,15d%n",
                    number,
                    city.getName(),
                    city.getCountryName(),
                    city.getPopulation()
            ));

            number++;
        }

        return sb.toString();
    }

    // 5. printTopNCapitalCitiesByContinent()
    /**
     * Formats a report of the top N most populated capital cities
     * in a specified continent, ordered by population from largest to smallest.
     *
     * @param topN the number of capital cities requested
     * @param continent the continent included in the report
     * @param capitalCities the list of capital cities to format
     * @return a formatted report containing the top N capital cities,
     *         their country names and populations
     */


    public static String printTopNCapitalCitiesByContinent(
            int topN, Continent continent, List<City> capitalCities) {

        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());

        // Report title
        sb.append("               ----- The top ")
                .append(topN)
                .append(" populated capital cities in ")
                .append(continent)
                .append(" organised by largest population to smallest -----")
                .append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-45s | %15s%n",
                "No.", "Name", "Country", "Population"));

        // Divider line under the header
        sb.append("-".repeat(119)).append(System.lineSeparator());

        // Number each capital city
        int number = 1;

        // One row per capital city (population gets commas, 1,234,567)
        for (City city : capitalCities) {
            sb.append(String.format("%-5d | %-45s | %-45s | %,15d%n",
                    number,
                    city.getName(),
                    city.getCountryName(),
                    city.getPopulation()
            ));

            number++;
        }

        return sb.toString();
    }

    // 6. printTopNCapitalCitiesByRegion()

    /**
     * Formats a report of the top N most populated capital cities
     * in a specified region, ordered by population from largest to smallest.
     *
     * @param topN the number of capital cities requested
     * @param region the region included in the report
     * @param capitalCities the list of capital cities to format
     * @return a formatted report containing the top N capital cities,
     *         their country names and populations
     *
     */


    public static String printTopNCapitalCitiesByRegion(
            int topN, String region, List<City> capitalCities) {

        StringBuilder sb = new StringBuilder();

        // Line separating reports
        sb.append("   ").append(System.lineSeparator());

        // Report title
        sb.append("               ----- The top ")
                .append(topN)
                .append(" populated capital cities in ")
                .append(region)
                .append(" organised by largest population to smallest -----")
                .append(System.lineSeparator());

        // Header row, with each column padded so everything lines up
        sb.append(String.format("%-5s | %-45s | %-45s | %15s%n",
                "No.", "Name", "Country", "Population"));

        // Divider line under the header
        sb.append("-".repeat(119)).append(System.lineSeparator());

        // Number each capital city
        int number = 1;

        // One row per capital city (population gets commas, 1,234,567)
        for (City city : capitalCities) {
            sb.append(String.format("%-5d | %-45s | %-45s | %,15d%n",
                    number,
                    city.getName(),
                    city.getCountryName(),
                    city.getPopulation()
            ));

            number++;
        }

        return sb.toString();
    }


}

