package com.napier.sem.services;

import com.napier.sem.models.Continent;
import com.napier.sem.reports.ReportFormatter;
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

        return ReportFormatter.formatPopulationDistribution(
                "CONTINENT POPULATION DISTRIBUTION REPORT",
                data
        );
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

        return ReportFormatter.formatPopulationDistribution(
                "REGION POPULATION DISTRIBUTION REPORT",
                data
        );
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

        return ReportFormatter.formatPopulationDistribution(
                "COUNTRY POPULATION DISTRIBUTION REPORT",
                data
        );
    }


    /**
     * Formats population data into a readable table.
     *
     * @param data the population data to display
     * @return formatted population distribution report
     */
    static String formatPopulationDistributionTable(
            List<PopulationDistributionData> data) {

        return ReportFormatter.formatPopulationDistribution(
                "POPULATION DISTRIBUTION REPORT",
                data
        );
    }
}