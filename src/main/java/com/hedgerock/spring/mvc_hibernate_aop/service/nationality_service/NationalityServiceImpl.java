package com.hedgerock.spring.mvc_hibernate_aop.service.nationality_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.nationality_dao.NationalityDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NationalityServiceImpl implements NationalityService{
    @Autowired
    private NationalityDAO nationalityDAO;

    @Override
    @Transactional
    public List<Nationality> getNationalities() {
        return this.nationalityDAO.getNationalities();
    }

    @Override
    @Transactional
    public Nationality saveCurrentNationality(Nationality nationality) {
        return this.nationalityDAO.saveNationality(nationality);
    }

    @Override
    @Transactional
    public Optional<Nationality> findNationality(Long natId) {
        return this.nationalityDAO.findNationality(natId);
    }

    @Override
    @Transactional
    public Optional<Nationality> findNationality(String natName) {
        return this.nationalityDAO.findNationality(natName);
    }

    @Override
    @Transactional
    public void deleteNationality(Long natId) {
        this.nationalityDAO.deleteNationality(natId);
    }
}
