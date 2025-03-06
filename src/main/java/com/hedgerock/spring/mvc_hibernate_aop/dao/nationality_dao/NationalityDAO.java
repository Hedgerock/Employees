package com.hedgerock.spring.mvc_hibernate_aop.dao.nationality_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;

import java.util.List;
import java.util.Optional;

public interface NationalityDAO {
    List<Nationality>getNationalities();
    Nationality saveNationality(Nationality nationality);
    Optional<Nationality> findNationality(Long natId);
    Optional<Nationality> findNationality(String natName);
    void deleteNationality(Long natId);
}
