package com.napier.sem;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.repositories.CityRepository;
import com.napier.sem.services.CityService;
import com.napier.sem.services.CapitalCityService;
import com.napier.sem.models.City;
import com.napier.sem.models.Continent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

//        CityRepository cityRepository = new CityRepository();
        CapitalCityService capitalCityService = new CapitalCityService();

        try {
            // First 10 capital cities
            System.out.println("=== First 10 Cities ===");

            for (City city : capitalCityService.getCapitalCities()) {

                System.out.println(city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


            // top 15 european capital cities
        try {
            System.out.println("\n=== Top 15 Capital Cities in Europe ===");

            for (City city : capitalCityService.getTopNCapitalCitiesByContinent(15, Continent.EUROPE)) {
                System.out.println(city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Top 20 capital cities by population
        try {
            System.out.println("\n=== Top 20 Capitals in the World ===");

            for (City city : capitalCityService.getTopNCapitalCities(20)) {
                System.out.println(city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Top 10 capital cities by population in North America - getCapitalCitiesByContinent
        try {
            System.out.println("\n=== Top 10 Capitals in North America===");

            for (City city : capitalCityService.getCapitalCitiesByContinent(Continent.NORTH_AMERICA)) {

                System.out.println(city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Top 10 capital cities in Polynesia by population
        try {
            System.out.println("\n=== Top 10 Capitals in Polynesia ===");

            for (City city : capitalCityService.getTopNCapitalCitiesByRegion("Polynesia", 10)) {
                System.out.println(city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Top 10 capital cities in Africa by population
        try {
            System.out.println("\n=== Top 10 Capitals in Africa ===");

            for (City city : capitalCityService.getTopNCapitalCitiesByContinent(10, Continent.AFRICA)) {
                System.out.println(city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //  get top 15 capitals in Australia and New Zealand - getTopNCapitalCitiesByRegion

        try {
            System.out.println("\n=== Top 15 Capitals in Australia and New Zealand ===");

            for (City city : capitalCityService.getTopNCapitalCitiesByRegion("Australia and New Zealand", 15)) {
                System.out.println(city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

