package com.napier.sem.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

import com.napier.sem.repositories.LanguageRepository.LanguageData;

public class LanguageRepositoryTest {

    private final LanguageRepository repository = new LanguageRepository();

    /**
     * Test that languages are sorted by highest to lowest number of speakers
     */
    @Test
    void testLanguageOrder() throws  SQLException{
        List<LanguageData> result =
                repository.getLanguagePopulationData();

            // Result should be listed from largest to smallest total population
            for (int i = 0; i < result.size() - 1; i++){
                assertTrue(
                        result.get(i).speakers() >=
                                result.get(i + 1).speakers()
                );
        }

    }

    /**
     * Test that only require languages are retrieved
     */
    @Test
    void testRequiredLanguage() throws  SQLException{
        List<LanguageRepository.LanguageData> result =
                repository.getLanguagePopulationData();

        // Ensure the result is not empty and has same size of row
        assertFalse(result.isEmpty());
        assertTrue(result.size() <= 6);

        // Check each result if language exists
        for (LanguageRepository.LanguageData row : result) {

            boolean validLanguage = false;

            for (LanguageRepository.Language language :
                    LanguageRepository.Language.values()) {

                if (language.name().equalsIgnoreCase(row.language())) {
                    validLanguage = true;
                    break;
                }
            }

            assertTrue(validLanguage);
        }
    }

    /**
     * Test that the world percentage is valid
     * @throws SQLException
     */
    @Test
    void testWorldPercentage() throws SQLException {

        List<LanguageData> results = repository.getLanguagePopulationData();

        for (LanguageData row : results) {
            // Check for null value
            assertNotNull(row.worldPercentage());

            // Check if percentage is valid
            assertTrue(row.worldPercentage() >= 0);
            assertTrue(row.worldPercentage() <= 100);
        }
    }

    /**
     * Test that the number of speaker is valid
     * @throws SQLException
     */
    @Test
    void testNumberOfSpeaker() throws SQLException {

        List<LanguageData> results = repository.getLanguagePopulationData();

        // Check for null value
        for (LanguageData row : results) {
            assertNotNull(row.speakers());
            assertTrue(row.speakers() >= 0);
        }
    }
}

