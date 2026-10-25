package com.napier.sem;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.repositories.CityRepository;
import com.napier.sem.models.City;
import com.napier.sem.models.Continent;

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
                if (count >= 10) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                count++;
            }

            // top 15 european cities
            System.out.println("\n=== Top 15 Cities in Europe ===");

            int c = 0;
            for (City city : cityRepository.getCitiesByContinent(Continent.EUROPE)) {
                if (c >= 15) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Top 20 capital cities by population - getAllCapitalCities
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

        // Top 10 capital cities by population in North America - getCapitalCitiesByContinent
        try {
            System.out.println("\n=== Top 10 Capitals in North America ===");

            int count = 0;

            for (City city : cityRepository.getTopNCapitalCitiesByContinent(10, Continent.NORTH_AMERICA)) {
                if (count >= 10) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Top 10 capital cities in region Polynesia - getTopNCapitalCitiesByRegion
        try {
            System.out.println("\n=== Top 10 Capitals in region of Polynesia ===");

            int count = 0;

            for (City city : cityRepository.getTopNCapitalCitiesByRegion(10, "Polynesia")) {
                if (count >= 10) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Top get 25 capitals in the world - getTopNCapitalCities

        try {
            System.out.println("\n=== Top 25 Cities ===");

            int count = 0;

            for (City city : cityRepository.getTopNCapitalCities(25)) {
                if (count >= 25) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                ;
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("\n=== Top 25 Cities ===");

            int count = 0;

            for (City city : cityRepository.getTopNCapitalCities(25)) {
                if (count >= 25) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                ;
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Top get 25 capitals in the world - getTopNCapitalCities

        try {
            System.out.println("\n=== Top 25 Cities ===");

            int count = 0;

            for (City city : cityRepository.getTopNCapitalCities(25)) {
                if (count >= 25) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                ;
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  get top 10 capitals in Africa - getTopNCapitalCitiesByContinent
        try {
            System.out.println("\n=== Top 10 capitals in Africa ===");

            int count = 0;

            for (City city : cityRepository.getTopNCapitalCitiesByContinent(10, Continent.AFRICA)) {
                if (count >= 10) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                ;
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  get top 15 capitals in Africa - getTopNCapitalCitiesByRegion

        try {
            System.out.println("\n=== Top 10 capitals in Australia and New Zealand ===");

            int count = 0;

            for (City city : cityRepository.getTopNCapitalCitiesByRegion(10, "Australia and New Zealand")) {
                if (count >= 10) break;

                System.out.println(city.getName() + " - " + city.getPopulation());
                ;
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

