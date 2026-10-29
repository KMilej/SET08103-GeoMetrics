package com.napier.sem;

import com.napier.sem.models.Continent;

import java.sql.SQLException;

import static com.napier.sem.services.CityReportService.*;
import static com.napier.sem.services.CountryService.*;

public class App {
    public static void main(String[] args) throws SQLException {
        // UC01 - Country Reports
        // System.out.println(getAllCountriesByPopulation());
        // System.out.println(getCountriesByContinentByPopulation(Continent.EUROPE));
        // System.out.println(getCountriesByRegionByPopulation("Western Europe"));
        // System.out.println(getTopNCountriesInWorld(10));
        // System.out.println(getTopNCountriesByContinent(10, Continent.EUROPE));
        // System.out.println(getTopNCountriesByRegion(10, "Western Europe"));

        // // UC02 - City Reports
        System.out.println(getAllCitiesByPopulation());
        System.out.println(getCitiesByContinentByPopulation(Continent.EUROPE));
        System.out.println(getCitiesByRegionByPopulation("Western Europe"));
        System.out.println(getCitiesByCountryByPopulation("GBR"));
        System.out.println(getCitiesByDistrictByPopulation("Scotland"));
        System.out.println(getTopNCitiesInWorld(10));
        System.out.println(getTopNCitiesByContinent(10, Continent.EUROPE));
        // System.out.println(getTopNCitiesByRegion(10, "Western Europe"));
        // System.out.println(getTopNCitiesByCountry(10, "GBR"));
        // System.out.println(getTopNCitiesByDistrict(10, "Scotland"));
        //
        // // UC03 - Capital City Reports
        // System.out.println(getAllCapitalCitiesByPopulation());
        // System.out.println(getCapitalCitiesByContinentByPopulation(Continent.EUROPE));
        // System.out.println(getCapitalCitiesByRegionByPopulation("Western Europe"));
        // System.out.println(getTopNCapitalCitiesInWorld(10));
        // System.out.println(getTopNCapitalCitiesByContinent(10, Continent.EUROPE));
        // System.out.println(getTopNCapitalCitiesByRegion(10, "Western Europe"));
        //
        // // UC04 - Population Distribution Reports
        // System.out.println(getUrbanAndRuralPopulationByContinent());
        // System.out.println(getUrbanAndRuralPopulationByRegion());
        // System.out.println(getUrbanAndRuralPopulationByCountry());
        //
        // // UC05 - Population Information
        // System.out.println(getWorldPopulation());
        // System.out.println(getContinentPopulation(Continent.EUROPE));
        // System.out.println(getRegionPopulation("Western Europe"));
        // System.out.println(getCountryPopulation("GBR"));
        // System.out.println(getDistrictPopulation("Scotland"));
        // System.out.println(getCityPopulation("Edinburgh"));
        //
        // // UC06 - Language Population Report
        // System.out.println(getLanguagePopulationReport());
    }

}
