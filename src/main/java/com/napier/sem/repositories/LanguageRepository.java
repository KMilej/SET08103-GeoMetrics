package com.napier.sem.repositories;
import com.napier.sem.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.SQLException;


public class LanguageRepository {

    /**
     * Stores language statistic data retrieved from the database.
     *
     * @param language the name of the language
     * @param speakers define total number of speakers
     * @param worldPercentage the total world percentage of people who speaks the language
     */
        public record LanguageData(
                String language,
                Long speakers,
                Double worldPercentage
        ) {}

    /**
     * Defines the languages required for the report
     */
    public enum Language {
        CHINESE,
        ENGLISH,
        HINDI,
        SPANISH,
        ARABIC,
        PORTUGUESE
    }


    /**
     * Retrieves language statistic data from the database.
     *
     * @return a list of language statistic ordered by number of speakers
     * @throws SQLException if a database access error occurs
     */
    public List<LanguageData> getLanguagePopulationData ()
            throws SQLException {

        // List to store the query results
        List<LanguageData> results = new ArrayList<>();

        // Get language defined by the enum
        Language[] languages = Language.values();

        // Generale SQL placeholder for the languages
        String placeholders = String.join(
                ", ", Collections.nCopies(languages.length, "?")
        );

        String sql = """
                SELECT cl.Language as language,
                    ROUND(SUM(
                            c.population * cl.Percentage / 100)
                        ) AS speakers,
                    ROUND(SUM(
                        c.Population * cl.Percentage / 100)
                            / NULLIF(
                                (SELECT SUM(Population) FROM country), 0
                            ) * 100, 2
                        ) AS worldPercentage
                FROM countrylanguage cl
                JOIN country c
                    ON cl.CountryCode = c.Code
                WHERE cl.language IN(%s)
                GROUP BY cl.language
                ORDER BY speakers DESC 
                """.formatted(placeholders);

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            for (int i = 0; i < languages.length; i++){
                stmt.setString(i + 1, languages[i].name());
            }

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    LanguageData data = new LanguageData(
                            rs.getString("language"),
                            rs.getObject("speakers", Long.class),
                            rs.getObject("worldPercentage", Double.class)
                    );

                    results.add(data);
                }
            }

        } catch (SQLException e) {
            throw new SQLException(
                    "Failed to fetch language statistics", e
            );
        }

        return results;
    }
}