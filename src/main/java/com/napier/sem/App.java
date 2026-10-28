package com.napier.sem;

import com.napier.sem.database.DatabaseConnection;
import com.napier.sem.repositories.CityRepository;
import com.napier.sem.services.CityService;
import com.napier.sem.services.CapitalCityService;
import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.services.PopulationService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
       /*
        // UC01 - Country Reports
        System.out.println(getAllCountriesByPopulation());Collapse annotationCheck failure on line R8Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getCountriesByContinentByPopulation(Continent.EUROPE));Collapse annotationCheck failure on line R9Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getCountriesByRegionByPopulation("Western Europe"));Collapse annotationCheck failure on line R10Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getTopNCountriesInWorld(10));Collapse annotationCheck failure on line R11Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getTopNCountriesByContinent(10, Continent.EUROPE));Collapse annotationCheck failure on line R12Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getTopNCountriesByRegion(10, "Western Europe"));Collapse annotationCheck failure on line R13Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details

        // UC02 - City Reports
        System.out.println(getAllCitiesByPopulation());Collapse annotationCheck failure on line R16Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getCitiesByContinentByPopulation(Continent.EUROPE));Collapse annotationCheck failure on line R17Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getCitiesByRegionByPopulation("Western Europe"));Collapse annotationCheck failure on line R18Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getCitiesByCountryByPopulation("GBR"));Collapse annotationCheck failure on line R19Check failure: cannot find symbolGeoMetrics Build / GeoMetrics build actionView details
        System.out.println(getCitiesByDistrictByPopulation("Scotland"));
        System.out.println(getTopNCitiesInWorld(10));
        System.out.println(getTopNCitiesByContinent(10, Continent.EUROPE));
        System.out.println(getTopNCitiesByRegion(10, "Western Europe"));
        System.out.println(getTopNCitiesByCountry(10, "GBR"));
        System.out.println(getTopNCitiesByDistrict(10, "Scotland"));

        // UC03 - Capital City Reports
        System.out.println(getAllCapitalCitiesByPopulation());
        System.out.println(getCapitalCitiesByContinentByPopulation(Continent.EUROPE));
        System.out.println(getCapitalCitiesByRegionByPopulation("Western Europe"));
        System.out.println(getTopNCapitalCitiesInWorld(10));
        System.out.println(getTopNCapitalCitiesByContinent(10, Continent.EUROPE));
        System.out.println(getTopNCapitalCitiesByRegion(10, "Western Europe"));

        // UC04 - Population Distribution Reports
        System.out.println(getUrbanAndRuralPopulationByContinent());
        System.out.println(getUrbanAndRuralPopulationByRegion());
        System.out.println(getUrbanAndRuralPopulationByCountry());
           */

        // UC05 - Population Information Report
        System.out.println("==================================================");
        System.out.println("          POPULATION INFORMATION REPORT           ");
        System.out.println("==================================================");

        try {
            // 1. World Population
            int worldPop = getWorldPopulation();
            System.out.printf("%-25s : %,d%n", "World Population", worldPop);

            // 2. Continent Population
            int continentPop = getContinentPopulation(Continent.EUROPE);
            System.out.printf("%-25s : %,d%n", "Continent (Europe)", continentPop);

            // 3. Region Population
            int regionPop = getRegionPopulation("Western Europe");
            System.out.printf("%-25s : %,d%n", "Region (Western Europe)", regionPop);

            // 4. Country Population
            int countryPop = getCountryPopulation("GBR");
            System.out.printf("%-25s : %,d%n", "Country (GBR)", countryPop);

            // 5. District Population
            int districtPop = getDistrictPopulation("Scotland");
            System.out.printf("%-25s : %,d%n", "District (Scotland)", districtPop);

            System.out.println("--------------------------------------------------");
            System.out.println("SPECIFIC CITY DETAILS:");
            System.out.println("--------------------------------------------------");
            System.out.printf("%-10s | %-20s | %-15s%n", "ID", "City Name", "Population");
            System.out.println("--------------------------------------------------");

            // 6. City Population (Prints details for each matching city)
            List<City> cities = getCityPopulation("Edinburgh");
            if (cities == null || cities.isEmpty()) {
                System.out.println("No details found for the requested city.");
            } else {
                for (City city : cities) {
                    System.out.printf("%-10d | %-20s | %,15d%n",
                            city.getId(),
                            city.getName(),
                            city.getPopulation()
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error generating population report: " + e.getMessage());
        }

        System.out.println("==================================================");
        /*
        // UC06 - Language Population Report
        System.out.println(getLanguagePopulationReport());
         */
}



    private static List<City> getCityPopulation(String cityName) throws SQLException {
        PopulationService myPop = new PopulationService();
        return myPop.getCityPopulation(cityName);
    }

    private static int getDistrictPopulation(String districtName) {
        PopulationService myPop = new PopulationService();
        return myPop.getDistrictPopulation(districtName);
    }

    private static int getCountryPopulation(String countryName) {
        PopulationService myPop = new PopulationService();
        return myPop.getCountryPopulation(countryName);
    }

    private static int getRegionPopulation(String regionName) {
        PopulationService myPop = new PopulationService();
        return myPop.getRegionPopulation(regionName);
    }

    private static int getContinentPopulation(Continent continentName) {
        PopulationService myPop = new PopulationService();
        return myPop.getContinentPopulation(continentName);
    }

    private static int getWorldPopulation() {
        PopulationService myPop = new PopulationService();
        return myPop.getWorldPopulation();
    }
}