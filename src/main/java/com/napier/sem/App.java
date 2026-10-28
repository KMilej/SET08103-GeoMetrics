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
            // All capital cities worldwide
            System.out.println("=== All Capital Cities worldwide ===");
            int count = 0;
            for (City city : capitalCityService.getCapitalCities()) {
                count++;
                System.out.println(count +". " + city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

            // top 15 european capital cities
        try {
            System.out.println("\n=== Top 15 Capital Cities in Europe ===");
            int count = 0;
            for (City city : capitalCityService.getTopNCapitalCitiesByContinent(15, Continent.EUROPE)) {
                count++;
                System.out.println(count +". "+ city.getName() + " - " + city.getPopulation());
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
            System.out.println("\n=== Get Capitals in Australia and New Zealand ===");
            int count = 0;
            for (City city : capitalCityService.getCapitalCitiesByRegion("Australia and New Zealand")) {
                count++;
                System.out.println(count +". " + city.getName() + " - " + city.getPopulation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

