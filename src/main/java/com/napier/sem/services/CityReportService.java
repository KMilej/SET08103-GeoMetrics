package com.napier.sem.services;

import com.napier.sem.models.City;
import com.napier.sem.models.Continent;
import com.napier.sem.repositories.CityReportRepository;

import java.sql.SQLException;
import java.util.List;

/** UC02 city reports. Each method returns report text for the CLI to print. */
public class CityReportService {

    public static String getAllCitiesByPopulation() throws SQLException {
        CityReportRepository repository = new CityReportRepository();
        List<City> cities = repository.getAllCitiesByPopulation();

        if (cities.isEmpty()) {
            return "No cities found.";
        }

        StringBuilder report = new StringBuilder();
        report.append(String.format(
                "%-35s %-12s %-25s %12s%n",
                "City", "Country Code", "District", "Population"));

        for (City city : cities) {
            report.append(String.format(
                    "%-35s %-12s %-25s %12d%n",
                    city.getName(),
                    city.getCountryCode(),
                    city.getDistrict(),
                    city.getPopulation()));
        }

        return report.toString();
    }

    public static String getCitiesByContinentByPopulation(Continent continent) throws SQLException {
        // TODO: Build the report for all cities in the selected continent.
        return "";
    }

    public static String getCitiesByRegionByPopulation(String region) throws SQLException {
        // TODO: Build the report for all cities in the selected region.
        return "";
    }

    public static String getCitiesByCountryByPopulation(String countryCode) throws SQLException {
        // TODO: Build the report for all cities in the selected country.
        return "";
    }

    public static String getCitiesByDistrictByPopulation(String district) throws SQLException {
        // TODO: Build the report for all cities in the selected district.
        return "";
    }

    public static String getTopNCitiesInWorld(int topN) throws SQLException {
        // TODO: Build the report for the top N cities worldwide.
        return "";
    }

    public static String getTopNCitiesByContinent(int topN, Continent continent) throws SQLException {
        // TODO: Build the report for the top N cities in the selected continent.
        return "";
    }

    public static String getTopNCitiesByRegion(int topN, String region) throws SQLException {
        // TODO: Build the report for the top N cities in the selected region.
        return "";
    }

    public static String getTopNCitiesByCountry(int topN, String countryCode) throws SQLException {
        // TODO: Build the report for the top N cities in the selected country.
        return "";
    }

    public static String getTopNCitiesByDistrict(int topN, String district) throws SQLException {
        // TODO: Build the report for the top N cities in the selected district.
        return "";
    }
}
