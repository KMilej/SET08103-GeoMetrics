package com.napier.sem;

import com.napier.sem.models.Continent;
import com.napier.sem.models.City;
import com.napier.sem.reports.ReportMDWriter;
import com.napier.sem.services.CapitalCityService;
import com.napier.sem.reports.ReportFormatter;
import com.napier.sem.services.PopulationService;

import static com.napier.sem.services.CityReportService.*;
import static com.napier.sem.services.CountryService.*;
import static com.napier.sem.services.CapitalCityReportService.*;
import static com.napier.sem.services.PopulationDistributionService.*;
import static com.napier.sem.services.LanguageReportService.getLanguagePopulationReport;


import java.sql.SQLException;
import java.util.List;

import static com.napier.sem.services.LanguageReportService.getLanguagePopulationReport;

public class App {
    public static void main(String[] args) throws SQLException {

        // UC01 - Country Reports
        System.out.println(printAllCountriesByPopulation());
        ReportMDWriter.saveAsMarkdown("AllCountriesByPopulation.md", printAllCountriesByPopulation());

        System.out.println(printCountriesByContinentByPopulation(Continent.EUROPE));
        ReportMDWriter.saveAsMarkdown("CountriesByContinentByPopulation.md", printCountriesByContinentByPopulation(Continent.EUROPE));

        System.out.println(printCountriesByRegionByPopulation("Western Europe"));
        ReportMDWriter.saveAsMarkdown("CountriesByRegionByPopulation.md", printCountriesByRegionByPopulation("Western Europe"));

        System.out.println(printTopNCountriesInWorld(10));
        ReportMDWriter.saveAsMarkdown("TopNCountriesInWorld.md", printTopNCountriesInWorld(10));

        System.out.println(printTopNCountriesByContinent(10, Continent.EUROPE));
        ReportMDWriter.saveAsMarkdown("TopNCountriesByContinent.md", printTopNCountriesByContinent(10, Continent.EUROPE));

        System.out.println(printTopNCountriesByRegion(10, "Western Europe"));
        ReportMDWriter.saveAsMarkdown("TopNCountriesByRegion.md", printTopNCountriesByRegion(10, "Western Europe"));

        // UC02 - City Reports
        System.out.println(getAllCitiesByPopulation());
        ReportMDWriter.saveAsMarkdown("AllCitiesByPopulation.md", getAllCitiesByPopulation());

        System.out.println(getCitiesByContinentByPopulation(Continent.EUROPE));
        ReportMDWriter.saveAsMarkdown("CitiesByContinentByPopulation.md", getCitiesByContinentByPopulation(Continent.EUROPE));

        System.out.println(getCitiesByRegionByPopulation("Western Europe"));
        ReportMDWriter.saveAsMarkdown("CitiesByRegionByPopulation.md", getCitiesByRegionByPopulation("Western Europe"));

        System.out.println(getCitiesByCountryByPopulation("United Kingdom"));
        ReportMDWriter.saveAsMarkdown("CitiesByCountryByPopulation.md", getCitiesByCountryByPopulation("United Kingdom"));

        System.out.println(getCitiesByDistrictByPopulation("Scotland"));
        ReportMDWriter.saveAsMarkdown("CitiesByDistrictByPopulation.md", getCitiesByDistrictByPopulation("Scotland"));

        System.out.println(getTopNCitiesInWorld(10));
        ReportMDWriter.saveAsMarkdown("TopNCitiesInWorld.md", getTopNCitiesInWorld(10));

        System.out.println(getTopNCitiesByContinent(10, Continent.EUROPE));
        ReportMDWriter.saveAsMarkdown("TopNCitiesByContinent.md", getTopNCitiesByContinent(10, Continent.EUROPE));

        System.out.println(getTopNCitiesByRegion(10, "Western Europe"));
        ReportMDWriter.saveAsMarkdown("TopNCitiesByRegion.md", getTopNCitiesByRegion(10, "Western Europe"));

        System.out.println(getTopNCitiesByCountry(10, "United Kingdom"));
        ReportMDWriter.saveAsMarkdown("TopNCitiesByCountry.md", getTopNCitiesByCountry(10, "United Kingdom"));

        System.out.println(getTopNCitiesByDistrict(10, "Scotland"));
        ReportMDWriter.saveAsMarkdown("TopNCitiesByDistrict.md", getTopNCitiesByDistrict(10, "Scotland"));

        // UC03 - Capital City Reports
        System.out.println(printAllCapitalCitiesByPopulation());
        ReportMDWriter.saveAsMarkdown("AllCapitalCitiesByPopulation.md", printAllCapitalCitiesByPopulation());

        System.out.println(printCapitalCitiesByContinentByPopulation(Continent.EUROPE));
        ReportMDWriter.saveAsMarkdown("CapitalCitiesByContinentByPopulation.md", printCapitalCitiesByContinentByPopulation(Continent.EUROPE));

        System.out.println(printCapitalCitiesByRegionByPopulation("Caribbean"));
        ReportMDWriter.saveAsMarkdown("CapitalCitiesByRegionByPopulation.md", printCapitalCitiesByRegionByPopulation("Caribbean"));

        System.out.println(printTopNCapitalCitiesInWorld(10));
        ReportMDWriter.saveAsMarkdown("TopNCapitalCitiesInWorld.md", printTopNCapitalCitiesInWorld(10));

        System.out.println(printTopNCapitalCitiesByContinent(10, Continent.SOUTH_AMERICA));
        ReportMDWriter.saveAsMarkdown("TopNCapitalCitiesByContinent.md", printTopNCapitalCitiesByContinent(10, Continent.SOUTH_AMERICA));

        System.out.println(printTopNCapitalCitiesByRegion(5, "Polynesia"));
        ReportMDWriter.saveAsMarkdown("TopNCapitalCitiesByRegion.md", printTopNCapitalCitiesByRegion(5, "Polynesia"));

        // UC04 - Population Distribution Reports
        System.out.println(getUrbanAndRuralPopulationByContinent(Continent.ASIA));
        ReportMDWriter.saveAsMarkdown("UrbanRuralPopulationByContinent.md", getUrbanAndRuralPopulationByContinent(Continent.ASIA));

        System.out.println(getUrbanAndRuralPopulationByRegion("Western Europe"));
        ReportMDWriter.saveAsMarkdown("UrbanRuralPopulationByRegion.md", getUrbanAndRuralPopulationByRegion("Western Europe"));

        System.out.println(getUrbanAndRuralPopulationByCountry("Poland"));
        ReportMDWriter.saveAsMarkdown("UrbanRuralPopulationByCountry.md", getUrbanAndRuralPopulationByCountry("Poland"));

        // UC05 - Population Information
        PopulationService populationService = new PopulationService();

        populationService.getWorldPopulation();

        long worldPopulation = populationService.getWorldPopulation();
        String WorldPopText = ReportFormatter.formatPopulation("GLOBAL", "Whole World", worldPopulation);
        ReportMDWriter.saveAsMarkdown("WorldPopulation.md", WorldPopText);

        populationService.getContinentPopulation(Continent.EUROPE);

        long continentPopulation = populationService.getContinentPopulation(Continent.EUROPE);
        String ContPopText = ReportFormatter.formatPopulation("CONTINENT", Continent.EUROPE.name(), continentPopulation);
        ReportMDWriter.saveAsMarkdown("ContinentPopulation.md", ContPopText);

        populationService.getRegionPopulation("Western Europe");

        long regionPopulation = populationService.getRegionPopulation("Western Europe");
        String RegPopText = ReportFormatter.formatPopulation("REGION", "Western Europe",regionPopulation);
        ReportMDWriter.saveAsMarkdown("RegionPopulation.md", RegPopText);


        populationService.getCountryPopulation("United Kingdom");

        long countryPopulation = populationService.getCountryPopulation("United Kingdom");
        String CPText = ReportFormatter.formatPopulation("COUNTRY", "United Kingdom", countryPopulation);
        ReportMDWriter.saveAsMarkdown("CountryPopulation.md", CPText);

        populationService.getDistrictPopulation("Scotland");

        long districtPopulation = populationService.getDistrictPopulation("Scotland");
        String DistPopText = ReportFormatter.formatPopulation("DISTRICT", "Scotland", districtPopulation);
        ReportMDWriter.saveAsMarkdown("DistrictPopulation.md", DistPopText);

        populationService.getCityPopulation("Edinburgh");

        List<City> cityPopulation = populationService.getCityPopulation("Edinburgh");
        String CitPopText = ReportFormatter.formatCityPopulation("Edinburgh", cityPopulation);
        ReportMDWriter.saveAsMarkdown("CityPopulation.md", CitPopText);

        // UC06 - Language Population Report
        System.out.println(getLanguagePopulationReport());
        ReportMDWriter.saveAsMarkdown("LanguagePopulationReport.md", getLanguagePopulationReport());
    }
}
