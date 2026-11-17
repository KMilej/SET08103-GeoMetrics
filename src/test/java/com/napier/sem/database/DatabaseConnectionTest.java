package com.napier.sem.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_DB_URL_TESTS", matches = "true")
    void connect_returnsValidConnection() throws SQLException {

        try (Connection c = DatabaseConnection.connect()) {
            assertNotNull(c, "Connection should not be null");
            assertTrue(c.isValid(2), "Connection should be valid");
        }
    }
}