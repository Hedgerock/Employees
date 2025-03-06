package com.hedgerock.spring.mvc_hibernate_aop.service.nationality_service;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;

import java.util.List;
import java.util.Optional;

public interface NationalityService {
    List<Nationality>getNationalities();
    Nationality saveCurrentNationality(Nationality nationality);
    Optional<Nationality> findNationality(Long natId);
    Optional<Nationality> findNationality(String natName);
    void deleteNationality(Long natId);
}
