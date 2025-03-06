package com.hedgerock.spring.mvc_hibernate_aop.dao.employee_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.QueryTemplates.*;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        final Session session = this.sessionFactory.getCurrentSession();

        String entityName = getEntityName(TEMPLATE_OF_GET_ALL_REQUEST + " WHERE fireDate is NULL", "Employee");

        Query<Employee> query = initQuery(
                Employee.class, session, entityName);

        return initPage(session, pageable, query, entityName);
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable, boolean isLookingForFired) {
        final Session session = this.sessionFactory.getCurrentSession();
        String entityName = getEntityName(TEMPLATE_OF_GET_ALL_REQUEST + " WHERE fireDate IS NOT NULL " +
                        "ORDER BY fireDate DESC",
                "Employee");

        Query<Employee> query = initQuery(
                Employee.class, session, entityName);

        return initPage(session, pageable, query, entityName);
    }

    @Override
    public Employee saveNewEmployee(Employee employee, Long depId, Long cityId, Long natId) {
        final Session session = this.sessionFactory.getCurrentSession();
        return saveSingleEmployee(employee, depId, cityId, natId, session);
    }

    @Override
    public Employee saveNewEmployee(Employee employee) {
        final Session session = this.sessionFactory.getCurrentSession();
        return session.merge(employee);
    }

    @Override
    public Employee findEmployee(Long id) {
        final Session session = this.sessionFactory.getCurrentSession();

        return session.find(Employee.class, id);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.remove(employee);
    }

    @Override
    public Page<Employee> getSearchedEmployees(String search, Pageable pageable) {
        final Session session = this.sessionFactory.getCurrentSession();

       String query = getSearchParams(search) + " AND fireDate IS NULL";

        Query<Employee> employeeQuery = getReadyQuery(
                search,
                session,
                query
        );

        int total = (int) employeeQuery.getResultCount();
        return initPage(pageable, employeeQuery, total);
    }

    @Override
    public Page<Employee> getSearchedEmployees(String search, Pageable pageable, boolean isLookingForFired) {
        final Session session = this.sessionFactory.getCurrentSession();

        String query = getSearchParams(search) + " AND fireDate IS NOT NULL";

        Query<Employee> employeeQuery = getReadyQuery(
                search,
                session,
                query
        );

        int total = (int) employeeQuery.getResultCount();
        return initPage(pageable, employeeQuery, total);
    }

    @Override
    public Page<Employee> findEmployeesInCurrentPlace(
            Long depId, String search, String tableFieldName, Pageable pageable) {
        final Session session = this.sessionFactory.getCurrentSession();

        String query = getSearchParams(search);

        String entityName = query +
                getEntityName(TEMPLATE_OF_JOINING_QUERY + TEMPLATE_OF_INSERTING_QUERY_PARAMS + " AND fireDate IS NULL",
                        tableFieldName, ":currentId");

        Query<Employee> employeeQuery = getReadyQuery(
                search,
                session,
                entityName,
                depId
        );

        int total = (int) employeeQuery.getResultCount();
        return initPage(pageable, employeeQuery, total);
    }


    @Override
    public Page<Employee> findEmployeesInCurrentPlace(Long id, String tableFieldName, Pageable pageable) {
        Session session = this.sessionFactory.getCurrentSession();

        String entityName = "FROM Employee WHERE " + tableFieldName + " = :id AND fireDate IS NULL";
        Query<Employee> employeeQuery = session.createQuery(entityName,
                Employee.class);
        employeeQuery.setParameter("id", id);

        int total = (int) employeeQuery.getResultCount();
        return initPage(pageable, employeeQuery, total);
    }


    @Override
    public List<Employee> findEmployeeInCurrentPlace(Long placeId, String entityName) {
        final Session session = this.sessionFactory.getCurrentSession();

        String hql = String.format("FROM Employee WHERE %s = :id", entityName);

        Query<Employee> employeeQuery = session.createQuery(hql, Employee.class);
        employeeQuery.setParameter("id", placeId);

        return employeeQuery.getResultList();
    }

    @Override
    public List<Employee> getSpecificEmployees(String tableFieldName) {
        final Session session = this.sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder
                        .and(criteriaBuilder
                                .isNull(root.get(tableFieldName)),
                        criteriaBuilder
                                .isNull(root.get("fireDate"))
                        )
                );

        Query<Employee> employeeQuery = session.createQuery(criteriaQuery);

        return employeeQuery.getResultList();
    }

    @Override
    public List<Employee> getSelectedEmployees(List<Long> id) {
        final Session session = this.sessionFactory.getCurrentSession();

        Query<Employee> employeeQuery = session.createQuery("FROM Employee WHERE id IN (:ids)", Employee.class);
        employeeQuery.setParameterList("ids", id);

        return employeeQuery.getResultList();
    }



    @Override
    public void saveAllEmployees(List<Employee> employees) {
        final Session session = this.sessionFactory.getCurrentSession();
        employees.forEach(employee -> {
            Long depId = employee.getDepId();
            Long cityId = employee.getCityId();
            Long natId = employee.getNationalityId();

            saveSingleEmployee(employee, depId, cityId, natId, session);
        });
    }
}
