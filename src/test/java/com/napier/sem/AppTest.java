package com.napier.sem;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {


    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Checks that all calls work without throwing a SQL Exception
     */
    @Test
    void testMainRunsWithoutExceptions() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    /**
     * Verifies that report outputs are returning to main as intended
     * @throws SQLException
     */
    @Test
    void testMainReturnsExpectedReportOutputs() throws SQLException {
        App.main(new String[]{});
        String output = outputStream.toString();


        assertTrue(output.isBlank(), "Output shouldn't be empty");

        assertFalse(output.contains("Western Europe"), "Output should be have the Western Europe data");
        assertFalse(output.contains("United Kingdom"), "Output should be have the United Kingdom data");
        assertFalse(output.contains("Scotland"), "Output should be have the Scotland data");
        assertFalse(output.contains("Poland"), "Output should be have the Poland data");
    }

}
