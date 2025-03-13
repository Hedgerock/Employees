package com.hedgerock.spring.mvc_hibernate_aop.dao.general_info_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.User;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.GeneralInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil.initPage;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil.updateStatistics;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.QueryTemplates.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.QueryTemplates.QUERY_WITH_PLACE_AND_ID;

@Repository
public class GeneralInfoDAOImpl implements GeneralInfoDAO {
    private static final String FULL_JOINS = "JOIN FETCH e.employeeDetails det " +
            "LEFT JOIN FETCH det.employeeDescription " +
            "LEFT JOIN FETCH det.emails " +
            "LEFT JOIN FETCH det.phoneNumbers " +
            "LEFT JOIN FETCH det.socialMedia sm " +
            "LEFT JOIN FETCH sm.telegram " +
            "LEFT JOIN FETCH sm.viber " +
            "LEFT JOIN FETCH sm.whatsApp " +
            "LEFT JOIN FETCH sm.linkedIn " +
            "LEFT JOIN FETCH det.picture pct " +
            "LEFT JOIN FETCH pct.pictures " +
            "LEFT JOIN FETCH e.department " +
            "LEFT JOIN FETCH e.city " +
            "LEFT JOIN FETCH e.nationality ";

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public GeneralInfo getGeneralInfo() {
        final Session session = this.sessionFactory.getCurrentSession();

        String queryParam = "SELECT " +
                "(SELECT COUNT(e.id) FROM employees e WHERE e.fire_date is NULL), " +
                "(SELECT COUNT(d.id) FROM departments d), " +
                "(SELECT COUNT(c.id) FROM cities c), " +
                "(SELECT COUNT(n.id) FROM nationalities n), " +
                "(SELECT SUM(e.salary) FROM employees e WHERE e.fire_date is NULL)";

        Query<Object[]>query = session.createNativeQuery(queryParam, Object[].class);
        Object[] value = query.uniqueResult();
        GeneralInfo generalInfo = new GeneralInfo();

        Long totalEmployees = value[0] != null ? (((Number) value[0]).longValue()) : 0;
        generalInfo.setTotalEmployees(totalEmployees);

        Long totalDepartments = value[1] != null ? (((Number) value[1]).longValue()) : 0;
        generalInfo.setTotalDepartments(totalDepartments);

        Long totalCities = value[2] != null ? (((Number) value[2]).longValue()) : 0;
        generalInfo.setTotalCities(totalCities);

        Long totalNationalities = value[3] != null ? (((Number) value[3]).longValue()) : 0;
        generalInfo.setTotalNationalities(totalNationalities);

        Long rawSum = value[4] != null ? (((Number) value[4]).longValue()) : 0;
        generalInfo.setTotalSalary(rawSum);

        return generalInfo;
    }

    @Override
    public <T> List<T> findEntityWithSiblings(
            Long id, String entityName, Class<T> tClass) {
        Session session = this.sessionFactory.getCurrentSession();

        String hql;

        if (tClass != Employee.class) {
            hql = String.format(QUERY_WITH_SIBLINGS, entityName) +
                    String.format(QUERY_WITHOUT_PLACE, entityName, entityName);
        } else {
            hql = String.format("SELECT e FROM %s e ", entityName) + FULL_JOINS + "WHERE " +
                    String.format(QUERY_WITHOUT_PLACE_AND_NOT_FIRED, entityName, entityName);
        }


        Query<T> query = session.createQuery(hql, tClass);

        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public <T> List<T> findEntityWithSiblings(Long id, Class<T> tClass) {
        Session session = this.sessionFactory.getCurrentSession();

        String entityName = "Employee";

        String hql = String.format("SELECT e FROM %s e ", entityName) + FULL_JOINS + "WHERE " +
                String.format(QUERY_WITHOUT_PLACE_AND_FIRED, entityName, entityName);

        Query<T> query = session.createQuery(hql, tClass);

        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public <T> List<T> findEntityWithSiblingsAndCurrentPlace(
            Long id, Long locId, String tableFieldName, String entityName, Class<T>tClass) {
        Session session = this.sessionFactory.getCurrentSession();

        String hql;

        if (tClass == Employee.class) {
            hql = String.format("SELECT e FROM %s e ", entityName) + FULL_JOINS + "WHERE " +
                    String.format(QUERY_WITH_PLACE, tableFieldName) +
                    " AND " +
                    String.format(QUERY_WITH_PLACE_AND_ID_AND_NOT_FIRED, entityName, tableFieldName, entityName, tableFieldName);
        } else {
            hql = String.format(QUERY_WITH_SIBLINGS, entityName) +
                    String.format(QUERY_WITH_PLACE, tableFieldName) +
                    " AND " +
                    String.format(QUERY_WITH_PLACE_AND_ID, entityName, tableFieldName, entityName, tableFieldName);
        }

        Query<T> query = session.createQuery(
                hql,
                tClass
        );

        query.setParameter("id", id);
        query.setParameter("currentId", locId);

        return query.getResultList();
    }

    @Override
    public <T> void deleteCurrentEntity(T entity, String tableFieldName, List<Long>ids, Class<T>tClass) {
        final Session session = this.sessionFactory.getCurrentSession();

        String queryBody = String.format("UPDATE Employee SET %s = NULL WHERE id IN (:ids)",
                tableFieldName);

        MutationQuery query = session.createMutationQuery(queryBody);

        query.setParameterList("ids", ids);
        query.executeUpdate();

        session.remove(entity);
    }

    @Override
    public <T> T saveCurrentEntity(T entity) {
        final Session session = this.sessionFactory.getCurrentSession();
        return session.merge(entity);
    }

    @Override
    public <T> Page<T> getCurrentEntitiesList(Class<T> tClass, String entityName, Pageable pageable) {
        final Session session = this.sessionFactory.getCurrentSession();

        Query<T> query = session.createQuery(String.format("FROM %s", entityName), tClass);

        int total = (int) query.getResultCount();
        return initPage(pageable, query, total);
    }

    @Override
    public <T> List<T> getCurrentEntitiesList(Class<T> tClass, String entityName) {
        final Session session = this.sessionFactory.getCurrentSession();
        Query<T> query = session.createQuery(String.format("FROM %s", entityName), tClass);

        return query.getResultList();
    }

    @Override
    public <T> List<T> getCurrentEntitiesList(Class<T> tClass, String entityName, String fieldName, Long id) {
        final Session session = this.sessionFactory.getCurrentSession();
        String hql = String.format("FROM %s WHERE %s = :id", entityName, fieldName);
        Query<T> query = session.createQuery(hql, tClass);

        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public <T> Optional<T> findCurrentEntity(Long id, String entityName, Class<T> tClass) {
        final Session session = this.sessionFactory.getCurrentSession();

        char entityAbbr = entityName.charAt(0);
        String prefix = entityAbbr + ".";

        String hql;

        if (tClass == Employee.class) {
            hql = String.format("SELECT e FROM %s e " + FULL_JOINS + "WHERE e.id = :id AND e.fireDate IS NULL", entityName);
        } else {
            hql = String.format("SELECT " + entityAbbr + " FROM %s " + entityAbbr +
                    " LEFT JOIN FETCH " + prefix + "employee" +
                    " WHERE " + prefix + "id = :id", entityName);
        }

        Query<T> tQuery = session.createQuery(hql, tClass);
        tQuery.setParameter("id", id);

        return Optional.ofNullable(tQuery.uniqueResult());
    }

    @Override
    public <T> Optional<T> findCurrentEntity(String name, String entityName, String fieldName, Class<T> tClass) {
        final Session session = this.sessionFactory.getCurrentSession();

        String hql = String.format("FROM %s WHERE %s = :name", entityName, fieldName);

        Query<T> query = session.createQuery(hql, tClass);
        query.setParameter("name", name);

        return Optional.ofNullable(query.uniqueResult());
    }

    @Override
    public <T> Optional<T> findFiredEmployee(Long id, Class<T> tClass) {
        Session session = this.sessionFactory.getCurrentSession();

        String hql = "FROM Employee WHERE id = :id AND fireDate IS NOT NULL";

        Query<T> employeeQuery = session.createQuery(hql, tClass);

        employeeQuery.setParameter("id", id);

        return Optional.ofNullable(employeeQuery.uniqueResult());
    }

    @Override
    public <T> Page<T> getCurrentEntitiesPage(Class<T> tClass, String entityName, Pageable pageable) {
        final Session session = this.sessionFactory.getCurrentSession();

        String hql = String.format("FROM %s entity ORDER BY entity.creationDate DESC",
                entityName);

        Query<T> query = session.createQuery(hql, tClass);

        int total = (int) query.getResultCount();

        return initPage(pageable, query, total);
    }
}
