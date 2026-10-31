package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.models.Continent;
import com.napier.sem.models.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Handles all the database queries to do with countries.
 * Go-to place for grabbing country data from the database.
 */
public class CountryRepository {

     /**
      * Grabs every country in the database, biggest population first.
      * Each country also comes with the name of its capital city.
      *
      * @return a list of all countries, sorted by population (highest to lowest)
      * @throws SQLException if something goes wrong talking to the database
      */
    public List<Country> getAllCountries()
            throws SQLException {
         // This will hold all the countries we pull out
        List<Country> allCountries = new ArrayList<>();

         // Join country with city so we can show the capital's name instead of just its ID
        String sql = """
                        SELECT co.Code, 
                               co.Name AS Name, 
                               co.Continent, 
                               co.Region, 
                               co.Population, 
                               ci.Name AS Capital 
                        FROM country co LEFT JOIN city ci on ci.ID = co.Capital 
                        ORDER BY co.Population DESC
                """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

             // Go through each row and turn it into a Country object
            while (rs.next()) {
                Country country = new Country(
                        rs.getString("Code"),
                        rs.getString("Name"),
                        Continent.continentText(rs.getString("Continent")),
                        rs.getString("Region"),
                        rs.getInt("Population"),
                        rs.getString("Capital")
                );

                 // Add it to our list
                 allCountries.add(country);
            }
        }
        return allCountries;
    }



    /**
     * Gets every country in one continent, biggest population first.
     *
     * @param continent the continent to look up
     * @return a list of countries in that continent, sorted by population (highest to lowest)
     * @throws SQLException if something goes wrong talking to the database
     */
    public List<Country> getAllCountriesByContinent(Continent continent)
            throws SQLException {
        // This will hold all the countries we pull out
        List<Country> allCountriesByContinent = new ArrayList<>();

        // Join country with city so we can show the capital's name instead of just its ID
        String sql = """
                    SELECT co.Code, 
                           co.Name AS Name, 
                           co.Continent, 
                           co.Region, 
                           co.Population, 
                           ci.Name AS Capital 
                    FROM country co LEFT JOIN city ci on ci.ID = co.Capital 
                    WHERE co.Continent = ?
                    ORDER BY co.Population DESC
            """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            // Fill in the ? with the continent name (EUROPE becomes "Europe")
            stmt.setString(1, continent.name().replace("_", " "));

            try (ResultSet rs = stmt.executeQuery()) {

                // Go through each row and turn it into a Country object
                while (rs.next()) {
                    Country country = new Country(
                            rs.getString("Code"),
                            rs.getString("Name"),
                            Continent.continentText(rs.getString("Continent")),
                            rs.getString("Region"),
                            rs.getInt("Population"),
                            rs.getString("Capital")
                    );

                    // Add it to our list
                    allCountriesByContinent.add(country);
                }
            }
        }
        return allCountriesByContinent;
    }

    /**
     * Grabs every country in one region, biggest population first.
     * Each country also comes with the name of its capital city.
     *
     * @param region the region to look up (e.g. "Western Europe")
     * @return a list of countries in that region, sorted by population (highest to lowest)
     * @throws SQLException if something goes wrong talking to the database
     */
    public List<Country> getAllCountriesByRegion(String region)
            throws SQLException {
        // This will hold all the countries we pull out
        List<Country> allCountriesByRegion = new ArrayList<>();

        // Join country with city so we can show the capital's name instead of just its ID
        String sql = """
                    SELECT co.Code, 
                           co.Name AS Name, 
                           co.Continent, 
                           co.Region, 
                           co.Population, 
                           ci.Name AS Capital 
                    FROM country co LEFT JOIN city ci on ci.ID = co.Capital 
                    WHERE co.Region = ?
                    ORDER BY co.Population DESC
            """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            // Fill in the ? with the region name
            stmt.setString(1, region);

            try (ResultSet rs = stmt.executeQuery()) {

                // Go through each row and turn it into a Country object
                while (rs.next()) {
                    Country country = new Country(
                            rs.getString("Code"),
                            rs.getString("Name"),
                            Continent.continentText(rs.getString("Continent")),
                            rs.getString("Region"),
                            rs.getInt("Population"),
                            rs.getString("Capital")
                    );

                    // Add it to our list
                    allCountriesByRegion.add(country);
                }
            }
        }
        return allCountriesByRegion;
    }

    /**
     * Grabs the top N most populated countries in the world, biggest first.
     * Each country also comes with the name of its capital city.
     *
     * @param topN how many countries to return (e.g. 10 for the top ten)
     * @return a list of the top N countries, sorted by population (highest to lowest)
     * @throws SQLException if something goes wrong talking to the database
     */
    public List<Country> getTopNCountriesInWorld(int topN)
            throws SQLException {
        // This will hold all the countries we pull out
        List<Country> topCountries = new ArrayList<>();

        // Join country with city so we can show the capital's name instead of just its ID
        String sql = """
                SELECT co.Code, 
                       co.Name AS Name, 
                       co.Continent, 
                       co.Region, 
                       co.Population, 
                       ci.Name AS Capital 
                FROM country co LEFT JOIN city ci on ci.ID = co.Capital
                ORDER BY co.Population DESC
                LIMIT ?
        """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            // Fill in the ? with how many countries we want
            stmt.setInt(1, topN);

            try (ResultSet rs = stmt.executeQuery()) {

                // Go through each row and turn it into a Country object
                while (rs.next()) {
                    Country country = new Country(
                            rs.getString("Code"),
                            rs.getString("Name"),
                            Continent.continentText(rs.getString("Continent")),
                            rs.getString("Region"),
                            rs.getInt("Population"),
                            rs.getString("Capital")
                    );

                    // Add it to our list
                    topCountries.add(country);
                }
            }
        }
        return topCountries;
    }





    /**
     * Grabs the top N most populated countries in one continent, biggest first.
     * Each country also comes with the name of its capital city.
     *
     * @param topN      how many countries to return (e.g. 10 for the top ten)
     * @param continent the continent to look up
     * @return a list of the top N countries in that continent, sorted by population (highest to lowest)
     * @throws IllegalArgumentException if topN is zero or negative
     * @throws SQLException if something goes wrong talking to the database
     */
    public List<Country> getTopNCountriesInWorldByContinent(int topN, Continent continent)
            throws SQLException {
        // A LIMIT of zero or less makes no sense, so stop early
        if (topN <= 0) {
            throw new IllegalArgumentException("topN must be greater than 0");
        }

        // This will hold all the countries we pull out
        List<Country> topCountries = new ArrayList<>();

        // Join country with city so we can show the capital's name instead of just its ID
        String sql = """
            SELECT co.Code, 
                   co.Name AS Name, 
                   co.Continent, 
                   co.Region, 
                   co.Population, 
                   ci.Name AS Capital 
            FROM country co LEFT JOIN city ci on ci.ID = co.Capital
            WHERE co.Continent = ? 
            ORDER BY co.Population DESC
            LIMIT ?
    """;

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            // Fill in the ?s: first the continent name, then how many countries we want
            stmt.setString(1, continent.name().replace("_", " "));
            stmt.setInt(2, topN);

            try (ResultSet rs = stmt.executeQuery()) {

                // Go through each row and turn it into a Country object
                while (rs.next()) {
                    Country country = new Country(
                            rs.getString("Code"),
                            rs.getString("Name"),
                            Continent.continentText(rs.getString("Continent")),
                            rs.getString("Region"),
                            rs.getInt("Population"),
                            rs.getString("Capital")
                    );

                    // Add it to our list
                    topCountries.add(country);
                }
            }
        }
        return topCountries;
    }


    /**
     * Grabs the top N most populated countries in one region, biggest first.
     * Each country also comes with the name of its capital city.
     *
     * @param topN   how many countries to return (e.g. 10 for the top ten)
     * @param region the region to look up (e.g. "Western Europe")
     * @return a list of the top N countries in that region, sorted by population (highest to lowest)
     * @throws IllegalArgumentException if topN is zero or negative
     * @throws SQLException if something goes wrong talking to the database
     */
    public List<Country> getTopNCountriesInWorldByRegion(int topN, String region)
            throws SQLException {
        // A LIMIT of zero or less makes no sense, so stop early
        if (topN <= 0) {
            throw new IllegalArgumentException("topN must be greater than 0");
        }

        // This will hold all the countries we pull out
        List<Country> topCountries = new ArrayList<>();

        // Join country with city so we can show the capital's name instead of just its ID
        String sql = """
        SELECT co.Code, 
               co.Name AS Name, 
               co.Continent, 
               co.Region, 
               co.Population, 
               ci.Name AS Capital 
        FROM country co LEFT JOIN city ci on ci.ID = co.Capital
        WHERE co.Region = ? 
        ORDER BY co.Population DESC
        LIMIT ?
""";

        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            // Fill in the ?s: first the region name, then how many countries we want
            stmt.setString(1, region);
            stmt.setInt(2, topN);

            try (ResultSet rs = stmt.executeQuery()) {

                // Go through each row and turn it into a Country object
                while (rs.next()) {
                    Country country = new Country(
                            rs.getString("Code"),
                            rs.getString("Name"),
                            Continent.continentText(rs.getString("Continent")),
                            rs.getString("Region"),
                            rs.getInt("Population"),
                            rs.getString("Capital")
                    );

                    // Add it to our list
                    topCountries.add(country);
                }
            }
        }
        return topCountries;
    }

}

