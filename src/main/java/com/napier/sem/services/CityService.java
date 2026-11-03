package com.napier.sem.services;

import com.napier.sem.models.City;
import com.napier.sem.repositories.CityRepository;

import java.sql.SQLException;
import java.util.List;

public class CityService {
    private final CityRepository cityRepository = new CityRepository();

    //getAllCitiesReport

    /**
     * Retrieves a report of all cities worldwide,
     * ordered by population from largest to smallest.
     *
     * @return a list of all cities sorted by population
     */

    public List<City> getAllCitiesReport() throws SQLException {
        return cityRepository.getAllCities();
    }

}
