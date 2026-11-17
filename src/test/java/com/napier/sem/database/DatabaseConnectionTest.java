package com.napier.sem.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for DatabaseConnection.
 * Needs the MySQL database to be running, so it only runs
 * when the RUN_DB_URL_TESTS environment variable is "true".
 */
class DatabaseConnectionTest {

    /**
     * Checks that connect() returns a working connection to the database.
     *
     * @throws SQLException if the database cannot be reached
     */
    @Test
    // Skip this test unless the environment variable is set,
    // so machines without a database do not fail
    @EnabledIfEnvironmentVariable(named = "RUN_DB_URL_TESTS", matches = "true")
    void connect_returnsValidConnection() throws SQLException {

        // try-with-resources closes the connection automatically,
        // even if an assertion fails
        try (Connection c = DatabaseConnection.connect()) {

            // A connection object must have been returned
            assertNotNull(c, "Connection should not be null");

            // The connection must be usable (waits up to 2 seconds)
            assertTrue(c.isValid(2), "Connection should be valid");
        }
    }


    /**
     * Calls the default constructor so the class line is covered.
     * DatabaseConnection only has static methods, so nothing else creates it.
     */
    @Test
    void constructor_canBeCreated() {
        assertNotNull(new DatabaseConnection());
    }

}