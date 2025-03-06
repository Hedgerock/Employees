package com.hedgerock.spring.mvc_hibernate_aop.dao.general_info_dao;

import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.GeneralInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GeneralInfoDAO {
    GeneralInfo getGeneralInfo();

    <T> void deleteCurrentEntity(T entity, String tableFieldName, List<Long>ids, Class<T>tClass);
    <T> T saveCurrentEntity(T entity);
    <T> Page<T> getCurrentEntitiesList(Class<T> tClass, String entityName, Pageable pageable);
    <T> List<T> getCurrentEntitiesList(Class<T> tClass, String entityName);
    <T> Optional<T> findCurrentEntity(Long id, String entityName, Class<T> tClass);
    <T> Optional<T> findCurrentEntity(String name, String entityName, String fieldName, Class<T>tClass);

    <T> Optional<T> findFiredEmployee(Long id, Class<T> tClass);

    <T> void updateEntityStatistic(T entity, Long id, String queryName);

    <T> List<T> findEntityWithSiblings(Long id, String entityName, Class<T>tClass);
    <T> List<T> findEntityWithSiblings(Long id, Class<T> tClass);
    <T> List<T> findEntityWithSiblingsAndCurrentPlace(
            Long id, Long locId, String tableFieldName, String entityName, Class<T>tClass);

    <T> Page<T> getCurrentEntitiesPage(Class<T> tClass, String entityName, Pageable pageable);
}
