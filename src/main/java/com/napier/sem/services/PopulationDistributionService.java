package com.napier.sem.services;

import com.napier.sem.models.Continent;
import com.napier.sem.repositories.PopulationRepository;
import com.napier.sem.repositories.PopulationRepository.PopulationDistributionData;

import java.util.List;

public class PopulationDistributionService {

    private static final PopulationRepository repository =
            new PopulationRepository();

    /**
     * Retrieves population distribution for a continent.
     *
     * @param continent the selected continent
     * @throws IllegalArgumentException if continent is empty
     * @return population distribution data
     */
    public static String getUrbanAndRuralPopulationByContinent(
            Continent continent) {

        if (continent == null) {
            throw new IllegalArgumentException("Continent cannot be null");
        }

        List<PopulationDistributionData> data = repository.getPopulationDistributionData(
                "CONTINENT",
                continent.name().replace("_", " ")
        );

        return formatPopulationDistributionTable(data);
    }

    /**
     * Retrieves population distribution for a region.
     *
     * @param region the selected region
     * @throws IllegalArgumentException if region is empty
     * @return population distribution data
     */

    public static String getUrbanAndRuralPopulationByRegion(
            String region) {

        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Region is required");
        }

        List<PopulationDistributionData> data =
                repository.getPopulationDistributionData("REGION", region);

        return formatPopulationDistributionTable(data);
    }

    /**
     * Retrieves population distribution for a country.
     *
     * @param countryCode the selected country code
     * @throws IllegalArgumentException if country code is empty
     * @return population distribution data
     */
    public static String getUrbanAndRuralPopulationByCountry(
            String countryCode) {

        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException("Country Code cannot be null");
        }

        List<PopulationDistributionData> data =
                repository.getPopulationDistributionData("COUNTRY", countryCode);

        return formatPopulationDistributionTable(
                data.stream().toList()
        );
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