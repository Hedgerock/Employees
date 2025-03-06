package com.hedgerock.spring.mvc_hibernate_aop.dao.city_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;

import java.util.List;
import java.util.Optional;

public interface CityDAO {
    List<City>getCities();
    City saveCity(City city);
    Optional<City> findCity(Long cityId);
    Optional<City> findCurrentCity(String cityName);
    void deleteCity(Long cityId);
}
