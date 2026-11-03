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
     * @param countryName the selected country name
     * @return population distribution data
     * @throws IllegalArgumentException if country name is null or blank
     * @throws SQLException if a database access error occurs
     */
    public static String getUrbanAndRuralPopulationByCountry(
            String countryName) throws SQLException{

        if (countryName == null || countryName.isBlank()) {
            throw new IllegalArgumentException(
                    "Country name is required"
            );
        }

        List<PopulationDistributionData>
                data = repository.getPopulationDistributionData
                ("COUNTRY", countryName);

        return formatPopulationDistributionTable(data);
    }


    /**
     * Formats population data into a readable table.
     *
     * @param data the population data to display
     * @return formatted population distribution report
     */
    static String formatPopulationDistributionTable(
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

            // Check if population data is invalid
            boolean invalid = total <= 0 || city < 0 || city > total;

            String cityText;
            String nonCityText;
            String cityPercent;
            String nonCityPercent;

            if (invalid) {
                cityText = "N/A";
                nonCityText = "N/A";
                cityPercent = "N/A";
                nonCityPercent = "N/A";
            } else {
                // Valid population data
                long nonCity = total - city;

                cityText = String.format("%,d", city);
                nonCityText = String.format("%,d", nonCity);

                cityPercent = String.format(
                        "%.2f%%", (double) city / total * 100
                );

                nonCityPercent = String.format(
                        "%.2f%%", (double) nonCity / total * 100
                );

            }

            table.append(String.format(
                    "%-40.40s | %18s | %18s | %12s | %12s%n",
                    row.location(),
                    cityText,
                    nonCityText,
                    cityPercent,
                    nonCityPercent
            ));
        }
        table.append("_".repeat(116)).append("\n");

        return table.toString();
    }
}