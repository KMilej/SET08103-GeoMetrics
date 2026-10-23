package com.napier.sem;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.repositories.CityRepository;
import com.napier.sem.models.City;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        // Load MySQL database driver
        try (Connection con = DatabaseConnection.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT Name, Population FROM country LIMIT 5")) {

            System.out.println("Successfully connected to MySQL!");

            while (rs.next()) {
                System.out.println(
                        rs.getString("Name") + " - "
                                + rs.getLong("Population")
                );
            }

            // call city report
            CityRepository cityRepository = new CityRepository();

            int count = 0;

            for (City city : cityRepository.getAllCities()) {
                if (count >= 30) break;

                System.out.println(city.getName());
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}