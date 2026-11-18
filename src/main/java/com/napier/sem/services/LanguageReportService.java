package com.napier.sem.services;

import com.napier.sem.reports.ReportFormatter;
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
     * Retained for compatibility with existing callers and tests.
     * The actual formatting is handled by ReportFormatter.
     *
     * @param results the language statistic to format
     * @return the formatted table
     */
    public static String formatLanguageReport(List<LanguageData> results){

        return ReportFormatter.formatLanguages(results);


    }
}