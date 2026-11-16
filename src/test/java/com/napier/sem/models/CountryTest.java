package com.napier.sem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the getters of Country.
 * Each test constructs a fully populated country and verifies that a single
 * getter returns the value that was passed to the constructor.
 */
class CountryTest {

    /** The country instance under test, rebuilt before each test. */
    private Country country;

    /**
     * Creates a fully populated Country - Poland before each test so
     * every getter can be checked against a known value.
     */
    @BeforeEach
    void setUp() {
        country = new Country("POL", "Poland", Continent.EUROPE, "Eastern Europe",
                new BigDecimal("323250.00"), (short) 1918, 38653600,
                new BigDecimal("73.2"), new BigDecimal("151697.00"), new BigDecimal("135636.00"),
                "Polska", "Republic", "Aleksander Kwasniewski", 2928, "PL");
    }

    /**
     * Verifies that getCode() returns the three-letter country code
     * passed to the constructor.
     */
    @Test
    void getCode_returnsCode() {
        assertEquals("POL", country.getCode());
    }

    /**
     * Verifies that getName() returns the English country name
     * passed to the constructor.
     */
    @Test
    void getName_returnsName() {
        assertEquals("Poland", country.getName());
    }

    /**
     * Verifies that getContinent() returns the Continent
     * passed to the constructor.
     */
    @Test
    void getContinent_returnsContinent() {
        assertEquals(Continent.EUROPE, country.getContinent());
    }

    /**
     * Verifies that getRegion() returns the region name
     * passed to the constructor.
     */
    @Test
    void getRegion_returnsRegion() {
        assertEquals("Eastern Europe", country.getRegion());
    }

    /**
     * Verifies that getSurfaceArea() returns the surface area
     * passed to the constructor.
     */
    @Test
    void getSurfaceArea_returnsSurfaceArea() {
        assertEquals(new BigDecimal("323250.00"), country.getSurfaceArea());
    }

    /**
     * Verifies that getIndepYear() returns the independence year
     * passed to the constructor.
     */
    @Test
    void getIndepYear_returnsIndepYear() {
        assertEquals((short) 1918, country.getIndepYear());
    }

    /**
     * Verifies that getPopulation() returns the population
     * passed to the constructor.
     */
    @Test
    void getPopulation_returnsPopulation() {
        assertEquals(38653600, country.getPopulation());
    }

    /**
     * Verifies that getLifeExpectancy() returns the life expectancy
     * passed to the constructor.
     */
    @Test
    void getLifeExpectancy_returnsLifeExpectancy() {
        assertEquals(new BigDecimal("73.2"), country.getLifeExpectancy());
    }

    /**
     * Verifies that getGnp() returns the gross national product
     * passed to the constructor.
     */
    @Test
    void getGnp_returnsGnp() {
        assertEquals(new BigDecimal("151697.00"), country.getGnp());
    }

    /**
     * Verifies that getGnpOld() returns the previous gross national
     * product passed to the constructor.
     */
    @Test
    void getGnpOld_returnsGnpOld() {
        assertEquals(new BigDecimal("135636.00"), country.getGnpOld());
    }

    /**
     * Verifies that getLocalName() returns the local-language name
     * passed to the constructor.
     */
    @Test
    void getLocalName_returnsLocalName() {
        assertEquals("Polska", country.getLocalName());
    }

    /**
     * Verifies that getGovernmentForm() returns the form of government
     * passed to the constructor.
     */
    @Test
    void getGovernmentForm_returnsGovernmentForm() {
        assertEquals("Republic", country.getGovernmentForm());
    }

    /**
     * Verifies that getHeadOfState() returns the head of state
     * passed to the constructor.
     */
    @Test
    void getHeadOfState_returnsHeadOfState() {
        assertEquals("Aleksander Kwasniewski", country.getHeadOfState());
    }

    /**
     * Verifies that getCapital() returns the capital city ID
     * passed to the constructor.
     */
    @Test
    void getCapital_returnsCapital() {
        assertEquals(2928, country.getCapital());
    }

    /**
     * Verifies that getCode2() returns the two-letter country code
     * passed to the constructor.
     */
    @Test
    void getCode2_returnsCode2() {
        assertEquals("PL", country.getCode2());
    }
}