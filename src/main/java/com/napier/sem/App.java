package com.napier.sem;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.repositories.CityRepository;
import com.napier.sem.models.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        CityRepository cityRepository = new CityRepository();
        try {
            // call city report (first 10 cities)
            System.out.println("=== First 10 Cities ===");

            int count = 0;

            for (City city : cityRepository.getAllCities()) {
                if (count >= 30) break;

                System.out.println(city.getName());
                count++;
            }

            // top 15 european cities
            System.out.println("\n=== Top 15 Cities in Europe ===");

            int c = 0;
            for (City city : cityRepository.getCitiesByContinent("Europe")) {
                if (c >= 15) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Top 20 capital cities by population
        try {
            System.out.println("\n=== Top 20 Capitals in the World ===");

            int count = 0;

            for (City city : cityRepository.getAllCapitalCities()) {
                if (count >= 20) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

