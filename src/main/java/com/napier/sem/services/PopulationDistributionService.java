package com.napier.sem.services;

import com.napier.sem.models.Continent;
import com.napier.sem.repositories.PopulationRepository;
import com.napier.sem.repositories.PopulationRepository.PopulationDistributionData;

import java.sql.SQLException;
import java.util.List;

public class PopulationDistributionService {

    private static final PopulationRepository repository =
            new PopulationRepository();

    /**
     * Retrieves opulation of people, people living in cities, and people
     * not living in cities in each continent.
     *
     * @param continent the selected continent
     * @return population distribution data
     * @throws IllegalArgumentException if continent is null
     * @throws SQLException if a database access error occurs
     */
    public static String getUrbanAndRuralPopulationByContinent(
            Continent continent) throws SQLException {

        if (continent == null) {
            throw new IllegalArgumentException(
                    "Continent is required"
            );
        }

        List<PopulationDistributionData> data =
                repository.getPopulationDistributionData (
                    "CONTINENT",
                    continent.name().replace("_", " ")
                );

        return formatPopulationDistributionTable(data);
    }

    /**
     * Retrieves  population of people, people living in cities,
     * and people not living in cities in each region.
     *
     * @param region the selected region
     * @return population distribution data
     * @throws IllegalArgumentException if region is null or blank
     * @throws SQLException if a database access error occurs
     */

    public static String getUrbanAndRuralPopulationByRegion(
            String region) throws SQLException{

        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException(
                    "Region is required"
            );
        }

        List<PopulationDistributionData> data =
                repository.getPopulationDistributionData("REGION", region);

        return formatPopulationDistributionTable(data);
    }

    /**
     * Retrieves population of people, people living in cities,
     * and people not living in cities in a country.
     *
     * @param countryCode the selected country code
     * @return population distribution data
     * @throws IllegalArgumentException if country code is null or blank
     * @throws SQLException if a database access error occurs
     */
    public static String getUrbanAndRuralPopulationByCountry(
            String countryCode) throws SQLException{

        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException(
                    "Country Code is required"
            );
        }

        List<PopulationDistributionData>
                data = repository.getPopulationDistributionData
                ("COUNTRY", countryCode);

        return formatPopulationDistributionTable(data);
    }


    /**
     * Formats population data into a readable table.
     *
     * @param data the population data to display
     * @return formatted population distribution report
     */
    private static String formatPopulationDistributionTable(
            List<PopulationDistributionData> data) {

        // Check if there is no data
        if (data.isEmpty()) {
            return "No data available.";
        }

        StringBuilder table = new StringBuilder();

        // Header
        table.append("_".repeat(116)).append("\n");
        table.append(String.format(
                "%-40s | %18s | %18s | %12s | %12s%n",
                "Location",
                "City Population",
                "Non-City Pop.",
                "City %",
                "Non-City %"
        ));

        // Separator
        table.append("-".repeat(116)).append("\n");

        // Data rows
        for (PopulationDistributionData row : data) {

            long total = row.totalPopulation();
            long city = row.cityPopulation();
            long nonCity = total - city;

            double cityPercent = total > 0
                    ? (double) city / total * 100
                    : 0;

            double nonCityPercent = total > 0
                    ? (double) nonCity / total * 100
                    : 0;

            table.append(String.format(
                    "%-40.40s | %,18d | %,18d | %11.2f%% | %11.2f%%%n",
                    row.location(),
                    city,
                    nonCity,
                    cityPercent,
                    nonCityPercent
            ));
        }
        table.append("_".repeat(116)).append("\n");

        return table.toString();
    }
}