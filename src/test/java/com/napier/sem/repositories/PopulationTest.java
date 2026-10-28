package com.napier.sem.repositories;

import com.napier.sem.models.Continent;
import com.napier.sem.services.PopulationService;
import com.napier.sem.database.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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

    @BeforeEach
    public void setUp() throws SQLException {
        populationService = new PopulationService();
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Wiring up standard JDBC mock behavior
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    public void testGetWorldPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Total")).thenReturn(1000000);

        // Mocking the static connection manager method
        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            int population = populationService.getWorldPopulation();

            // Assert
            assertEquals(1000000, population);
        }
    }

    @Test
    public void testGetContinentPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Total")).thenReturn(500000);

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            int population = populationService.getContinentPopulation(Continent.EUROPE);

            // Assert
            assertEquals(500000, population);
            verify(mockStatement, times(1)).setString(1, "EUROPE");
        }
    }

    @Test
    public void testGetRegionPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Total")).thenReturn(250000);

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            int population = populationService.getRegionPopulation("Western Europe");

            // Assert
            assertEquals(250000, population);
            verify(mockStatement, times(1)).setString(1, "Western Europe");
        }
    }

    @Test
    public void testGetCountryPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Total")).thenReturn(60000);

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            int population = populationService.getCountryPopulation("GBR");

            // Assert
            assertEquals(60000, population);
            verify(mockStatement, times(1)).setString(1, "GBR");
        }
    }

    @Test
    public void testGetDistrictPopulation_Success() throws SQLException {
        // Arrange
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Total")).thenReturn(5000);

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act
            int population = populationService.getDistrictPopulation("Scotland");

            // Assert
            assertEquals(5000, population);
            verify(mockStatement, times(1)).setString(1, "Scotland");
        }
    }

    @Test
    public void testGetWorldPopulation_ThrowsRuntimeExceptionOnSQLException() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Database offline"));

        try (MockedStatic<DatabaseConnection> mockedDb = Mockito.mockStatic(DatabaseConnection.class)) {
            mockedDb.when(DatabaseConnection::connect).thenReturn(mockConnection);

            // Act & Assert
            assertThrows(RuntimeException.class, () -> {
                populationService.getWorldPopulation();
            });
        }
    }
}