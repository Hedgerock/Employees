package com.hedgerock.spring.mvc_hibernate_aop.service.city_service;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City>getCities();
    City saveCurrentCity(City city);
    Optional<City>findCity(Long cityId);
    Optional<City>findCurrentCity(String cityName);
    void deleteCity(Long cityId);
}
