package com.napier.sem.models;

/**
 * The seven continents, matching the names used in the database.
 * Used for turning the plain text from a query into a proper Continent value.
 */
public enum Continent {
    AFRICA("Africa"),
    ANTARCTICA("Antarctica"),
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    OCEANIA("Oceania"),
    SOUTH_AMERICA("South America");


    // For readable name, exactly as it appears in the database (eg "Europe")
    private final String continentName;

    /**
     * Sets up each continent with its readable name.
     *
     * @param continentName the name as stored in the database
     */
    Continent(String continentName) {
        this.continentName = continentName;
    }

    /**
     * Turns the continent name from the database (eg "Europe")
     * into the matching Continent constant. Capital letters don't matter.
     *
     * @param text the continent name to look up
     * @return the matching Continent
     * @throws IllegalArgumentException if the name doesn't match any continent
     */

    public static Continent continentText(String text) {
        // Check each continent until we find one with the same name
        for (Continent continent : values()) {
            if (continent.continentName.equalsIgnoreCase(text)) {
                return continent;
            }
        }
        // Nothing matched, so let the caller know something's off
        throw new IllegalArgumentException("Unknown continent: " + text);
    }

    /***
     *
     * @return continentName as a string
     */
    @Override
    public String toString() {
        return continentName;          // prints "Europe", not EUROPE
    }
}
