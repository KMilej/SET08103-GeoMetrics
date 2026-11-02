package com.napier.sem.services;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.Continent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class PopulationServiceTest {

    private PopulationService populationService;
    private MockedStatic<DatabaseConnection> mockedDatabaseConnection;

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private AutoCloseable closeable; // Added to manage the Mockito session lifecycle

    /**
     * Initializes the service under test and creates default stubbed chains
     * for all JDBC elements prior to each test invocation.
     *
     * @throws SQLException if a simulated database error occurs during initialization
     */
    @BeforeEach
    void setUp() throws SQLException {
        // Open the Mockito session to process annotations cleanly without strict rules
        closeable = MockitoAnnotations.openMocks(this);

        populationService = new PopulationService();

        // Set up standard JDBC mocks
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Mock the static database connection method
        mockedDatabaseConnection = mockStatic(DatabaseConnection.class);
        mockedDatabaseConnection.when(DatabaseConnection::connect).thenReturn(mockConnection);

        // Fixed: Wrapped with lenient() so exception tests don't break on unused stubs
        lenient().when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        lenient().when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    /**
     * Closes the static mock resource instance to isolate tests and
     * prevent environment leakages across the test execution lifecycle.
     */
    @AfterEach
    void tearDown() throws Exception {
        // Deregister the static mock block to prevent memory leaks across test files
        mockedDatabaseConnection.close();
        // Close the mock session to release framework resources cleanly
        closeable.close();
    }


    /**
     * Verifies that {@link PopulationService#getCountryPopulation(String)} retrieves
     * the country population total under typical system flows.
     *
     * @throws SQLException if an unexpected database issue arises
     */
    @Test
    void testGetCountryPopulation_Success() throws SQLException {
        // Arrange
        String countryName = "United Kingdom";
        long expectedPopulation = 66_000_000L;

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(expectedPopulation);

        // Act
        long actualPopulation = populationService.getCountryPopulation(countryName);

        // Assert
        assertEquals(expectedPopulation, actualPopulation);
        verify(mockPreparedStatement).setString(1, countryName);
    }

    /**
     * Verifies that {@link PopulationService#getCountryPopulation(String)} converts
     * an underlying {@link SQLException} into a wrapped runtime exception.
     *
     * @throws SQLException if an unexpected error mapping fails
     */
    @Test
    void testGetCountryPopulation_ThrowsException() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Database offline"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            populationService.getCountryPopulation("France");
        });

        assertTrue(exception.getMessage().contains("Failed to fetch country population"));
    }

    /**
     * Verifies that {@link PopulationService#getDistrictPopulation(String)} retrieves
     * the district population total under typical system flows.
     *
     * @throws SQLException if an unexpected database issue arises
     */
    @Test
    void testGetDistrictPopulation_Success() throws SQLException {
        // Arrange
        String districtName = "Scotland";
        long expectedPopulation = 5_500_000L;

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(expectedPopulation);

        // Act
        long actualPopulation = populationService.getDistrictPopulation(districtName);

        // Assert
        assertEquals(expectedPopulation, actualPopulation);
        verify(mockPreparedStatement).setString(1, districtName);
    }

    /**
     * Verifies that {@link PopulationService#getDistrictPopulation(String)} converts
     * an underlying {@link SQLException} into a wrapped runtime exception.
     *
     * @throws SQLException if an unexpected error mapping fails
     */
    @Test
    void testGetDistrictPopulation_ThrowsException() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Query timeout"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            populationService.getDistrictPopulation("California");
        });

        assertTrue(exception.getMessage().contains("Failed to fetch district population"));
    }

    /**
     * Verifies that {@link PopulationService#getWorldPopulation()} retrieves
     * the world population total under typical system flows.
     *
     * @throws SQLException if an unexpected database issue arises
     */
    @Test
    void testGetWorldPopulation_Success() throws SQLException {
        // Arrange
        long expectedPopulation = 8_000_000_000L;

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(expectedPopulation);

        // Act
        long actualPopulation = populationService.getWorldPopulation();

        // Assert
        assertEquals(expectedPopulation, actualPopulation);
    }


    /**
     * Verifies that {@link PopulationService#getWorldPopulation()} converts
     * an underlying {@link SQLException} into a wrapped runtime exception.
     *
     * @throws SQLException if an unexpected error mapping fails
     */
    @Test
    void testGetWorldPopulation_ThrowsException() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Connection lost"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            populationService.getWorldPopulation();
        });

        assertTrue(exception.getMessage().contains("Failed to fetch world population"));
    }

    /**
     * Verifies that {@link PopulationService#getContinentPopulation(Continent)} retrieves
     * the continent population total under typical system flows.
     *
     * @throws SQLException if an unexpected database issue arises
     */
    @Test
    void testGetContinentPopulation_Success() throws SQLException {
        // Arrange
        Continent continent = Continent.EUROPE; // Assuming your Continent enum holds 'Europe'
        long expectedPopulation = 740_000_000L;

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(expectedPopulation);

        // Act
        long actualPopulation = populationService.getContinentPopulation(continent);

        // Assert
        assertEquals(expectedPopulation, actualPopulation);
        verify(mockPreparedStatement).setString(1, continent.name());
    }


    /**
     * Verifies that {@link PopulationService#getContinentPopulation(Continent)} converts
     * an underlying {@link SQLException} into a wrapped runtime exception.
     *
     * @throws SQLException if an unexpected error mapping fails
     */
    @Test
    void testGetContinentPopulation_ThrowsException() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Syntax error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            populationService.getContinentPopulation(Continent.ASIA);
        });

        assertTrue(exception.getMessage().contains("Failed to fetch continent population"));
    }

    /**
     * Verifies that {@link PopulationService#getRegionPopulation(String)} retrieves
     * the regional population total under typical system flows.
     *
     * @throws SQLException if an unexpected database issue arises
     */
    @Test
    void testGetRegionPopulation_Success() throws SQLException {
        // Arrange
        String regionName = "Western Europe";
        long expectedPopulation = 190_000_000L;

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Total")).thenReturn(expectedPopulation);

        // Act
        long actualPopulation = populationService.getRegionPopulation(regionName);

        // Assert
        assertEquals(expectedPopulation, actualPopulation);
        verify(mockPreparedStatement).setString(1, regionName);
    }

    /**
     * Verifies that {@link PopulationService#getRegionPopulation(String)} converts
     * an underlying {@link SQLException} into a wrapped runtime exception.
     *
     * @throws SQLException if an unexpected error mapping fails
     */
    @Test
    void testGetRegionPopulation_ThrowsException() throws SQLException {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Table not found"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            populationService.getRegionPopulation("Caribbean");
        });

        assertTrue(exception.getMessage().contains("Failed to fetch region population"));
    }
}
