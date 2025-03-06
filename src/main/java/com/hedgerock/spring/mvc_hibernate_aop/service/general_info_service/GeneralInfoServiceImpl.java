package com.hedgerock.spring.mvc_hibernate_aop.service.general_info_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.general_info_dao.GeneralInfoDAO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.GeneralInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralInfoServiceImpl implements GeneralInfoService {

    @Autowired
    private GeneralInfoDAO generalInfoDAO;


    @Override
    @Transactional
    public GeneralInfo getGeneralInfo() {
        return this.generalInfoDAO.getGeneralInfo();
    }

    @Override
    @Transactional
    public <T> Optional<T> findCurrentEntity(Long id, String entityName, Class<T> tClass) {
        return this.generalInfoDAO.findCurrentEntity(id, entityName, tClass);
    }

    @Override
    @Transactional
    public <T> Optional<T> findCurrentEntity(Long id, String entityName, Class<T> tClass, boolean isLookingForFired) {
        return !isLookingForFired
                ? findCurrentEntity(id, entityName, tClass)
                : this.generalInfoDAO.findFiredEmployee(id, tClass);
    }

    @Override
    @Transactional
    public <T> Optional<T> findCurrentEntity(String name, String entityName, Class<T> tClass) {
        return this.generalInfoDAO.findCurrentEntity(name, entityName, "name", tClass);
    }

    @Override
    @Transactional
    public <T> Optional<T> findCurrentEntity(String name, String entityName, String fieldName, Class<T> tClass) {
        return this.generalInfoDAO.findCurrentEntity(name, entityName, fieldName, tClass);
    }

    @Override
    @Transactional
    public <T> void updateEntityStatistic(T entity, Long id, String queryName) {
        this.generalInfoDAO.updateEntityStatistic(entity, id, queryName);
    }

    @Override
    @Transactional
    public <T> List<T> findEntityWithSiblings(Long id, String entityName, Class<T> tClass) {
        return this.generalInfoDAO.findEntityWithSiblings(id, entityName, tClass);
    }

    @Override
    @Transactional
    public <T> List<T> findEntityWithSiblings(Long id, Class<T> tClass) {
        return this.generalInfoDAO.findEntityWithSiblings(id, tClass);
    }

    @Override
    @Transactional
    public <T> List<T> findEntityWithSiblingsAndCurrentPlace(
            Long id, Long locId, String tableFieldName, String entityName, Class<T>tClass) {
        return this.generalInfoDAO.findEntityWithSiblingsAndCurrentPlace(
                id, locId, tableFieldName, entityName, tClass);
    }

    @Override
    @Transactional
    public <T> void deleteCurrentEntity(T entity, String tableFieldName, List<Long>ids, Class<T>tClass) {
        this.generalInfoDAO.deleteCurrentEntity(entity, tableFieldName, ids, tClass);
    }

    @Override
    @Transactional
    public <T> T saveCurrentEntity(T entity) {
        return this.generalInfoDAO.saveCurrentEntity(entity);
    }

    @Override
    @Transactional
    public <T> Page<T> getCurrentEntitiesList(Class<T> tClass, String entityName, Pageable pageable) {
        return this.generalInfoDAO.getCurrentEntitiesList(tClass, entityName, pageable);
    }

    @Override
    @Transactional
    public <T> List<T> getCurrentEntitiesList(Class<T> tClass, String entityName) {
        return this.generalInfoDAO.getCurrentEntitiesList(tClass, entityName);
    }

    @Override
    @Transactional
    public <T> Page<T> getCurrentEntitiesPage(Class<T> tClass, String entityName, Pageable pageable) {
        return this.generalInfoDAO.getCurrentEntitiesPage(tClass, entityName, pageable);
    }
}
