package com.napier.sem.services;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.napier.sem.repositories.LanguageRepository.LanguageData;

public class LanguageReportServiceTest {

    // Sample valid language data for unit testing
    private static final List<LanguageData> MOCK_DATA = List.of(
            new LanguageData("Japanese", 30000000L, 3.25),
            new LanguageData("French", 2000000L, 2.25)
    );

    // Sample invalid language data for unit testing
    private static final List<LanguageData> INVALID_DATA = List.of(
            new LanguageData("Simlish", 1000000000L, 15.50),
            new LanguageData("Over Percentage", 500000000L, 110.00),
            new LanguageData("Negative Percentage", 1000L, -10.00),
            new LanguageData("Negative Speaker", -10L, 0.00),
            new LanguageData("Null Language", null, 10.00)

    );

    /**
     * Test that required language are retrieved and not required are excluded
     */
    @Test
    void testGetLanguagePopulationData(){
        String report =
                LanguageReportService.formatLanguageReport(MOCK_DATA);

        // Check unsupported languages are excluded
        assertFalse(report.contains("Japanese"));
        assertFalse(report.contains("French"));

        // Check their statistics are also excluded
        assertFalse(report.contains("30,000,000"));
        assertFalse(report.contains("2,000,000"));
        assertFalse(report.contains("3.25%"));
        assertFalse(report.contains("2.25%"));

        // Check required languages are still displayed
        assertTrue(report.contains("CHINESE"));
        assertTrue(report.contains("ENGLISH"));
    }

    /**
     * Test invalid percentage are displayed as N/A
     */
    @Test
    void testInvalidPercentage(){
        String report =
                LanguageReportService.formatLanguageReport(INVALID_DATA);

        // Test Data
        //"Over Percentage", 500000000L, 110.00
        //"Negative Percentage", 1000L, -10.00

        assertFalse(report.contains("110.00%"));
        assertFalse(report.contains("-10.00%"));
        assertTrue(report.contains("N/A"));
    }

    /**
     * Test invalid total number of speakers are displayed as N/A
     */
    @Test
    void testInvalidNumberOfSpeakers(){
        String report =
                LanguageReportService.formatLanguageReport(INVALID_DATA);

        // Test Data
        // "Negative Speaker", -10L, 0.00
        // "Null Language", null, 10.00

        assertFalse(report.contains("null"));
        assertFalse(report.contains("-10L"));
        assertTrue(report.contains("N/A"));
    }
}
