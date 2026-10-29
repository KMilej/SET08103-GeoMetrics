package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;


public class PopulationRepository {

    /**
     * Stores population data retrieved from the database.
     *
     * @param location the geographical location
     * @param totalPopulation the total population
     * @param cityPopulation population recorded in cities
     */
    public record PopulationDistributionData(
            String location,
            long totalPopulation,
            long cityPopulation
    ) {}

    /**
     * Retrieves population data for a selected geographical scope.
     *
     * @param scope the geographical scope
     * @param location the continent, region or country code
     * @return a list of population data
     * @throws IllegalArgumentException if the scope is invalid
     * @throws RuntimeException if a database error occurs
     * @throws SQLException if a database access error occurs
     */
    public List<PopulationDistributionData> getPopulationDistributionData(
            String scope, String location)  throws SQLException {

        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException(
                    "Location cannot be empty"
            );
        }

        String filter = switch (scope) {
            case "CONTINENT" -> "c.Continent = ?";
            case "REGION" -> "c.Region = ?";
            case "COUNTRY" -> "c.Code = ?";
            default -> throw new IllegalArgumentException(
                    "Invalid scope: " + scope
            );
        };

        String sql = """
                SELECT c.Name AS location,
                       c.Population AS total_population,
                       COALESCE(cp.city_population, 0) AS city_population
                FROM country c
                LEFT JOIN (
                    SELECT CountryCode,
                           SUM(Population) AS city_population
                    FROM city
                    GROUP BY CountryCode
                ) cp ON c.Code = cp.CountryCode
                WHERE %s
                ORDER BY c.Population DESC
                """.formatted(filter);

        List<PopulationDistributionData> populationDistributionData = new ArrayList<>();

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setString(1, location);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    PopulationDistributionData data = new PopulationDistributionData(
                            rs.getString("location"),
                            rs.getLong("total_population"),
                            rs.getLong("city_population")
                    );

                    populationDistributionData.add(data);
                }
            }

        } catch (SQLException e) {
            throw new SQLException(
                    "Failed to fetch population distribution data", e
            );
        }

        return populationDistributionData;
    }
}