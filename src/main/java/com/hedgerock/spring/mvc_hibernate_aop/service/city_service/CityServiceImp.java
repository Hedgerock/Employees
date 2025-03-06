package com.hedgerock.spring.mvc_hibernate_aop.service.city_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.city_dao.CityDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImp implements CityService {

    @Autowired
    private CityDAO cityDao;

    @Override
    @Transactional
    public List<City> getCities() {
        return this.cityDao.getCities();
    }

    @Override
    @Transactional
    public City saveCurrentCity(City city) {
        return this.cityDao.saveCity(city);
    }

    @Override
    @Transactional
    public Optional<City> findCity(Long cityId) {
        return this.cityDao.findCity(cityId);
    }

    @Override
    @Transactional
    public Optional<City> findCurrentCity(String cityName) {
        return this.cityDao.findCurrentCity(cityName);
    }

    @Override
    @Transactional
    public void deleteCity(Long cityId) {
        this.cityDao.deleteCity(cityId);
    }
}
