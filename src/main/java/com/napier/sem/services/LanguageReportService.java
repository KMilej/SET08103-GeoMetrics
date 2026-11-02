package com.napier.sem.services;

import com.napier.sem.repositories.LanguageRepository;
import com.napier.sem.repositories.LanguageRepository.LanguageData;
import com.napier.sem.repositories.LanguageRepository.Language;

import java.sql.SQLException;
import java.util.List;

public class LanguageReportService {

    // Repository used to retrieve the language statistic from the db
    private static final LanguageRepository repository =
            new LanguageRepository();

    /**
     * Retrieve total number of
     * English, Chinese, Hindi, Spanish, Arabic, and Portuguese
     * speakers and their percentage of world population from the database
     *
     * @return the formatted language population report
     * @throws SQLException if a database access error occurs
     */
    public static String getLanguagePopulationReport() throws SQLException {
        // Retrieve language data
        List<LanguageData> results =
                repository.getLanguagePopulationData();

        return formatLanguageReport(results);
    }



    /**
     * Formats the language population statistic into a readable table
     * Displays N/A for missing data and ensure unsupported languages
     * are excluded
     *
     * @param results the language statistic to format
     * @return the formatted table
     */
    public static String formatLanguageReport(List<LanguageData> results){

        // Check if there is no data
        if (results.isEmpty()) {
            return "No data available.";
        }

        StringBuilder table = new StringBuilder();

        // Header
        String header = String.format(
                "%-25s | %20s | %20s%n",
                "Language", "No. of Speakers", "World Percentage"
        );

        // Separator
        String separator = "-".repeat(header.length());

        table.append(header);
        table.append(separator).append("\n");


        // Data rows
        for (LanguageData row : results) {
            // Check for valid languages
            boolean validLanguage = false;

            for (Language language : Language.values()) {
                if (language.name().equalsIgnoreCase(row.language())) {
                    validLanguage = true;
                    break;
                }
            }

            // Skip languages not required in the report
            if (!validLanguage) {
                continue;
            }

            // Check for total speakers
            String speakers;

            if(row.speakers() == null){
                speakers = "N/A";
            } else{
                speakers = String.format("%,d", row.speakers());
            }

            // Check for null or invalid percentage
            String percentage;
            Double value = row.worldPercentage();

            if (value == null || value > 100 || value < 0){
                percentage = "N/A";
            } else{
                percentage = String.format("%.2f%%", value);
            }

            // Append result to table
            table.append(String.format(
                    "%-25s | %20s | %20s%n",
                    row.language(),
                    speakers,
                    percentage
            ));


        }

        // Display missing languages as N/A
        for (Language language : Language.values()) {
            boolean exists = results.stream()
                    .anyMatch(row ->
                            row.language().equalsIgnoreCase(language.name())
                    );

            if (!exists) {
                table.append(String.format(
                        "%-25s | %20s | %20s%n",
                        language.name(), "N/A", "N/A"
                ));
            }
        }
        // Bottom separator
        table.append(separator);

        return table.toString();
    }
}