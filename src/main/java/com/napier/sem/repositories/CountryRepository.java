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
                        FROM country co JOIN city ci on ci.ID = co.Capital 
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
}

