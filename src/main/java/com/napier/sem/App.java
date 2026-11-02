package com.napier.sem;

import com.napier.sem.services.CityService;
import com.napier.sem.services.CapitalCityService;
import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.services.PopulationService;
import com.napier.sem.services.PopulationService.*;


import java.sql.SQLException;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {

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


        // ==========================================
        //              POPULATION REPORTS
        // ==========================================
        PopulationService populationService = new PopulationService();
        populationService.getWorldPopulation();
        populationService.getContinentPopulation(Continent.EUROPE);
        populationService.getRegionPopulation("Western Europe");
        populationService.getCountryPopulation("United Kingdom");
        populationService.getDistrictPopulation("Scotland");
        populationService.getCityPopulation("Edinburgh");


       /*
        // UC06 - Language Population Report
        System.out.println(getLanguagePopulationReport());
         */
}



}