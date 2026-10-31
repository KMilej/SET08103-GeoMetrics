package com.napier.sem.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the Continent enum: converting database text into a Continent
 * and printing a Continent as readable text.
 */
class ContinentTest {

    /**
     * Checks that there are exactly seven continents.
     */
    @Test
    void values_containsSevenContinents() {
        assertEquals(7, Continent.values().length);
    }

    /**
     * Checks that each database name maps to the right constant.
     */
    @Test
    void continentText_returnsMatchingContinent() {
        assertEquals(Continent.AFRICA, Continent.continentText("Africa"));
        assertEquals(Continent.ANTARCTICA, Continent.continentText("Antarctica"));
        assertEquals(Continent.ASIA, Continent.continentText("Asia"));
        assertEquals(Continent.EUROPE, Continent.continentText("Europe"));
        assertEquals(Continent.OCEANIA, Continent.continentText("Oceania"));
    }

    /**
     * Checks that continents with two words are matched.
     */
    @Test
    void continentText_acceptsContinentsWithTwoWords() {
        assertEquals(Continent.NORTH_AMERICA, Continent.continentText("North America"));
        assertEquals(Continent.SOUTH_AMERICA, Continent.continentText("South America"));
    }

    /**
     * Checks that capital letters do not matter.
     */
    @Test
    void continentText_ignoresCase() {
        assertEquals(Continent.EUROPE, Continent.continentText("EUROPE"));
        assertEquals(Continent.EUROPE, Continent.continentText("europe"));
        assertEquals(Continent.NORTH_AMERICA, Continent.continentText("nOrTh AmErIcA"));
    }

    /**
     * Checks that an unknown name is rejected and the message includes the name.
     */
    @Test
    void continentText_rejectsUnknownContinent() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Continent.continentText("Atlantis"));

        assertEquals("Unknown continent: Atlantis", exception.getMessage());
    }

    /**
     * Checks that null and empty text are rejected.
     */
    @Test
    void continentText_rejectsNullAndEmptyText() {
        assertThrows(IllegalArgumentException.class, () -> Continent.continentText(null));
        assertThrows(IllegalArgumentException.class, () -> Continent.continentText(""));
    }

    /**
     * Checks that the enum constant name (with an underscore) is not accepted as text.
     */
    @Test
    void continentText_rejectsConstantNameWithUnderscore() {
        assertThrows(IllegalArgumentException.class,
                () -> Continent.continentText("NORTH_AMERICA"));
    }

    /**
     * Checks that extra spaces are not trimmed.
     */
    @Test
    void continentText_rejectsTextWithExtraSpaces() {
        assertThrows(IllegalArgumentException.class, () -> Continent.continentText(" Europe"));
        assertThrows(IllegalArgumentException.class, () -> Continent.continentText("Europe "));
    }

    /**
     * Checks that toString gives the continent name as text, not the constant name.
     */
    @Test
    void toString_returnsContinentNameAsText() {
        assertEquals("Europe", Continent.EUROPE.toString());
        assertEquals("North America", Continent.NORTH_AMERICA.toString());
        assertEquals("South America", Continent.SOUTH_AMERICA.toString());
    }

    /**
     * Checks that no continent prints as an upper case constant name.
     */
    @Test
    void toString_neverContainsUnderscore() {
        for (Continent continent : Continent.values()) {
            assertTrue(!continent.toString().contains("_"));
        }
    }
}