package com.napier.sem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the getters of City.
 * Each test constructs a fully populated city and verifies that a single
 * getter returns the value that was passed to the constructor.
 */
class CityTest {

    /** The city instance under test, rebuilt before each test. */
    private City city;

    /**
     * Creates a fully populated City - Wroclaw before each test so
     * every getter can be checked against a known value.
     */
    @BeforeEach
    void setUp() {
        city = new City(2931, "Wroclaw", "POL", "Dolnoslaskie", 636765);
        city.setCountryName("Poland");
    }

    /**
     * Verifies that getId() returns the city ID
     * passed to the constructor.
     */
    @Test
    void getId_returnsId() {
        assertEquals(2931, city.getId());
    }

    /**
     * Verifies that getName() returns the city name
     * passed to the constructor.
     */
    @Test
    void getName_returnsName() {
        assertEquals("Wroclaw", city.getName());
    }

    /**
     * Verifies that getCountryCode() returns the three-letter
     * country code passed to the constructor.
     */
    @Test
    void getCountryCode_returnsCountryCode() {
        assertEquals("POL", city.getCountryCode());
    }

    /**
     * Verifies that getDistrict() returns the district
     * passed to the constructor.
     */
    @Test
    void getDistrict_returnsDistrict() {
        assertEquals("Dolnoslaskie", city.getDistrict());
    }

    /**
     * Verifies that getPopulation() returns the population
     * passed to the constructor.
     */
    @Test
    void getPopulation_returnsPopulation() {
        assertEquals(636765, city.getPopulation());
    }

    /**
     * Verifies that getCountryName() returns the country name
     * set via setCountryName().
     */
    @Test
    void getCountryName_returnsCountryName() {
        assertEquals("Poland", city.getCountryName());
    }

    /**
     * Verifies that setId() updates the city ID
     * and getId() returns the new value.
     */
    @Test
    void setId_updatesId() {
        city.setId(3068);
        assertEquals(3068, city.getId());
    }

    /**
     * Verifies that setName() updates the city name
     * and getName() returns the new value.
     */
    @Test
    void setName_updatesName() {
        city.setName("Berlin");
        assertEquals("Berlin", city.getName());
    }

    /**
     * Verifies that setDistrict() updates the district
     * and getDistrict() returns the new value.
     */
    @Test
    void setDistrict_updatesDistrict() {
        city.setDistrict("Berliini");
        assertEquals("Berliini", city.getDistrict());
    }

    /**
     * Verifies that setPopulation() updates the population
     * and getPopulation() returns the new value.
     */
    @Test
    void setPopulation_updatesPopulation() {
        city.setPopulation(3386667);
        assertEquals(3386667, city.getPopulation());
    }

}