package com.napier.sem.services;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.reports.ReportFormatter;

import java.sql.SQLException;
import java.util.List;

public class CapitalCityReportService {

    // Shared capital city service
    private static final CapitalCityService capitalCityService =
            new CapitalCityService();

    // UC03 - Capital City Reports

    // 1. All capital cities in the world
    public static String printAllCapitalCitiesByPopulation()
            throws SQLException {

        List<City> cities = capitalCityService.getCapitalCities();

        return ReportFormatter.printAllCapitalCities(
                "All Capital Cities by Population",
                cities
        );
    }



    // 2. All capital cities in a continent
    public static String printCapitalCitiesByContinentByPopulation(
            Continent continent) throws SQLException {

        List<City> cities =
                capitalCityService.getCapitalCitiesByContinent(continent);

        return ReportFormatter.printAllCapitalCitiesByContinent(
                continent,
                cities
        );
    }


    // 3. All capital cities in a region
    public static String printCapitalCitiesByRegionByPopulation(
            String region) throws SQLException {

        List<City> cities =
                capitalCityService.getCapitalCitiesByRegion(region);

        return ReportFormatter.printAllCapitalCitiesByRegion(
                region,
                cities
        );
    }


    // 4. Top N capital cities in the world
    public static String printTopNCapitalCitiesInWorld(
            int topN) throws SQLException {

        List<City> cities =
                capitalCityService.getTopNCapitalCities(topN);

        return ReportFormatter.printTopNCapitalCities(
                topN,
                cities
        );
    }


    // 5. Top N capital cities in a continent
    public static String printTopNCapitalCitiesByContinent(
            int topN, Continent continent) throws SQLException {

        List<City> cities =
                capitalCityService.getTopNCapitalCitiesByContinent(
                        topN, continent
                );

        return ReportFormatter.printTopNCapitalCitiesByContinent(
                topN,
                continent,
                cities
        );
    }


    // 6. Top N capital cities in a region
    public static String printTopNCapitalCitiesByRegion(
            int topN, String region) throws SQLException {

        List<City> cities =
                capitalCityService.getTopNCapitalCitiesByRegion(
                        region, topN
                );

        return ReportFormatter.printTopNCapitalCitiesByRegion(
                topN,
                region,
                cities
        );
    }

}

