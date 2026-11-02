package com.napier.sem;

import com.napier.sem.models.Continent;
import com.napier.sem.models.City;
import com.napier.sem.services.CapitalCityService;
import com.napier.sem.reports.ReportFormatter;
import com.napier.sem.services.PopulationService;

import static com.napier.sem.services.CityReportService.*;
import static com.napier.sem.services.CountryService.*;
import static com.napier.sem.services.CapitalCityReportService.*;
import static com.napier.sem.services.PopulationDistributionService.*;


import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) throws SQLException {

        // UC01 - Country Reports
        System.out.println(printAllCountriesByPopulation());
        System.out.println(printCountriesByContinentByPopulation(Continent.EUROPE));
        System.out.println(printCountriesByRegionByPopulation("Western Europe"));
        System.out.println(printTopNCountriesInWorld(10));
        System.out.println(printTopNCountriesByContinent(10, Continent.EUROPE));
        System.out.println(printTopNCountriesByRegion(10, "Western Europe"));

        // UC02 - City Reports
        System.out.println(getAllCitiesByPopulation());
        System.out.println(getCitiesByContinentByPopulation(Continent.EUROPE));
        System.out.println(getCitiesByRegionByPopulation("Western Europe"));
        System.out.println(getCitiesByCountryByPopulation("GBR"));
        System.out.println(getCitiesByDistrictByPopulation("Scotland"));
        System.out.println(getTopNCitiesInWorld(10));
        System.out.println(getTopNCitiesByContinent(10, Continent.EUROPE));
        System.out.println(getTopNCitiesByRegion(10, "Western Europe"));
        System.out.println(getTopNCitiesByCountry(10, "GBR"));
        System.out.println(getTopNCitiesByDistrict(10, "Scotland"));

        // UC03 - Capital City Reports

        // 1. All capital cities in the world
        System.out.println(printAllCapitalCitiesByPopulation());

        // 2. All capital cities in a continent
        System.out.println(printCapitalCitiesByContinentByPopulation(Continent.EUROPE));

        // 3. All capital cities in a region
        System.out.println(printCapitalCitiesByRegionByPopulation("Caribbean"));

        // 4. Top N capital cities in the world
        System.out.println(printTopNCapitalCitiesInWorld(10));

        // 5. Top N capital cities in a continent
        System.out.println(printTopNCapitalCitiesByContinent(10, Continent.SOUTH_AMERICA));

        // 6. Top N capital cities in a region
        System.out.println(printTopNCapitalCitiesByRegion(5, "Polynesia"));

        // UC04 - Population Distribution Reports
         System.out.println(getUrbanAndRuralPopulationByContinent(Continent.ASIA));
         System.out.println(getUrbanAndRuralPopulationByRegion("Western Europe"));
         System.out.println(getUrbanAndRuralPopulationByCountry("POL"));

        // UC05 - Population Information
        PopulationService populationService = new PopulationService();
        populationService.getWorldPopulation();
        populationService.getContinentPopulation(Continent.EUROPE);
        populationService.getRegionPopulation("Western Europe");
        populationService.getCountryPopulation("United Kingdom");
        populationService.getDistrictPopulation("Scotland");
        populationService.getCityPopulation("Edinburgh");
        // UC06 - Language Population Report
        // System.out.println(getLanguagePopulationReport());

    }

}

