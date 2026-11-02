package com.napier.sem.repositories;

import com.napier.sem.models.Continent;
import com.napier.sem.services.PopulationService;
import com.napier.sem.database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

    public class PopulationTest {

        private PopulationService populationService;
        private Connection mockConnection;
        private PreparedStatement mockStatement;
        private ResultSet mockResultSet;
        private AutoCloseable closeable; // Add this line

        @BeforeEach
        public void setUp() throws SQLException {
            // Initialize Mockito session cleanly
            closeable = MockitoAnnotations.openMocks(this);

            populationService = new PopulationService();
            mockConnection = mock(Connection.class);
            mockStatement = mock(PreparedStatement.class);
            mockResultSet = mock(ResultSet.class);

            // Wiring up standard JDBC mock behavior
            lenient().when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
            lenient().when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        }


        @AfterEach
        public void tearDown() throws Exception {
            closeable.close();
        }
    /**
     * test if the right world population has been fetched correctly
     *
     * @throws SQLException
     */
    @Test
    public void testGetWorldPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(1000000L); // Changed to getLong

        // Mocking the static connection manager method
        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            long population = populationService.getWorldPopulation();

            // Assert
            assertEquals(1000000L, population);
        }
    }

    /**
     * test if the right continent population has been fetched correctly
     *
     * @throws SQLException
     */
    @Test
    public void testGetContinentPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(500000L); // Changed to getLong

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            long population = populationService.getContinentPopulation(Continent.EUROPE);

            // Assert
            assertEquals(500000L, population);
            verify(mockStatement, times(1)).setString(1, "EUROPE");
        }
    }

    /**
     * test if the right region population has been fetched correctly
     *
     * @throws SQLException
     */
    @Test
    public void testGetRegionPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(250000L); // Changed to getLong

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            long population = populationService.getRegionPopulation("Western Europe");

            // Assert
            assertEquals(250000L, population);
            verify(mockStatement, times(1)).setString(1, "Western Europe");
        }
    }

    /**
     * test if the right country population has been fetched correctly
     *
     * @throws SQLException
     */
    @Test
    public void testGetCountryPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(60000L); // Changed to getLong

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            long population = populationService.getCountryPopulation("GBR");

            // Assert
            assertEquals(60000L, population);
            verify(mockStatement, times(1)).setString(1, "GBR");
        }
    }

    /**
     * test if the right district population has been fetched correctly
     *
     * @throws SQLException
     */
    @Test
    public void testGetDistrictPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(5000L); // Changed to getLong

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            long population = populationService.getDistrictPopulation("Scotland");

            // Assert
            assertEquals(5000L, population);
            verify(mockStatement, times(1)).setString(1, "Scotland");
        }
    }

    /**
     * test rhe runtime exception works correctly when trying to fetch the world population
     *
     * @throws SQLException
     */
    @Test
    public void testGetWorldPopulation_ThrowsRuntimeExceptionOnSQLException() throws SQLException {
        // Arrange
        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenThrow(new SQLException("Database offline"));

            // Act & Assert
            assertThrows(RuntimeException.class, () -> {
                populationService.getWorldPopulation();
            });
        }
    }
}
