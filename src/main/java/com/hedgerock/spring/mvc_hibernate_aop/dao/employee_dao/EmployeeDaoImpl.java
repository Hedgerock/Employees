package com.hedgerock.spring.mvc_hibernate_aop.dao.employee_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.QueryTemplates.JOINS;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.QueryTemplates.TEMPLATE_OF_JOINING_QUERY;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        final Session session = this.sessionFactory.getCurrentSession();

        String entityName = getEntityName("SELECT e FROM Employee e " +
                        JOINS + "WHERE e.fireDate IS NULL " + "ORDER BY e.fireDate DESC",
                "Employee");

        Query<Employee> query = initQuery(
                Employee.class, session, entityName);


        String counterQuery = "SELECT COUNT(e.id) FROM Employee e " +
                "WHERE e.fireDate IS NULL";


        return initPage(session, pageable, query, counterQuery);
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable, boolean isLookingForFired) {
        final Session session = this.sessionFactory.getCurrentSession();
        String entityName = getEntityName("SELECT e FROM Employee e " +
                        "LEFT JOIN FETCH e.department " +
                        "LEFT JOIN FETCH e.nationality " +
                        "LEFT JOIN FETCH e.city " +
                        "JOIN FETCH e.employeeDetails " +
                        " WHERE e.fireDate IS NOT NULL " +
                        "ORDER BY e.fireDate DESC",
                "Employee");

        Query<Employee> query = initQuery(
                Employee.class, session, entityName);

        String counterQuery = "SELECT COUNT(e.id) FROM Employee e " +
                "WHERE e.fireDate IS NOT NULL";


        return initPage(session, pageable, query, counterQuery);
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

       String query = getSearchParams(search) + " AND e.fireDate IS NULL";

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
                getEntityName(TEMPLATE_OF_JOINING_QUERY +
                                "e.%s = %s " +
                                "AND e.fireDate IS NULL",
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

        String entityName = "SELECT e FROM Employee e " + JOINS + "WHERE " + "e." + tableFieldName
                + " = :id AND e.fireDate IS NULL";

        Query<Employee> employeeQuery = session.createQuery(entityName,
                Employee.class);
        employeeQuery.setParameter("id", id);

        int total = (int) employeeQuery.getResultCount();
        return initPage(pageable, employeeQuery, total);
    }


    @Override
    public List<Employee> findEmployeeInCurrentPlace(Long placeId, String entityName) {
        final Session session = this.sessionFactory.getCurrentSession();

        String hql = String.format("SELECT e FROM Employee e " + JOINS + "WHERE e.%s = :id", entityName);

        Query<Employee> employeeQuery = session.createQuery(hql, Employee.class);
        employeeQuery.setParameter("id", placeId);

        return employeeQuery.getResultList();
    }

    @Override
    public List<Employee> getSpecificEmployees(String tableFieldName) {
        final Session session = this.sessionFactory.getCurrentSession();

//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//        Root<Employee> root = criteriaQuery.from(Employee.class);
//        criteriaQuery.select(root)
//                .where(criteriaBuilder
//                        .and(criteriaBuilder
//                                .isNull(root.get(tableFieldName)),
//                        criteriaBuilder
//                                .isNull(root.get("fireDate"))
//                        )
//                );

        String hql = String.format("SELECT e FROM Employee e " + JOINS + "WHERE e.%s IS NULL", tableFieldName);

        Query<Employee> employeeQuery = session.createQuery(hql, Employee.class);

        return employeeQuery.getResultList();
    }

    @Override
    public List<Employee> getSelectedEmployees(List<Long> id) {
        final Session session = this.sessionFactory.getCurrentSession();

        Query<Employee> employeeQuery = session.createQuery("SELECT e FROM Employee e WHERE e.id IN (:ids)", Employee.class);
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
