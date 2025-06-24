package com.hedgerock.spring.mvc_hibernate_aop.dao.department_dao;

import com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import jakarta.persistence.Query;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.QueryTemplates.*;

@Repository
public class DepartmentDAOImp implements DepartmentDAO {

    private final SessionFactory sessionFactory;

    public DepartmentDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Department> findCurrentDepartmentByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();

        String entityName = getEntityName(
                TEMPLATE_OF_ROOT_QUERY, "Department", "name", ":departmentName");

        org.hibernate.query.Query<Department> departmentQuery = initQuery(Department.class, session, entityName);
        departmentQuery.setParameter("departmentName", name);

        return Optional.ofNullable(departmentQuery.uniqueResult());
    }

    @Override
    public List<Department> getAllDepartments() {
        Session session = this.sessionFactory.getCurrentSession();
        String entityRequest = String.format(TEMPLATE_OF_GET_ALL_REQUEST, "Department");
        org.hibernate.query.Query<Department> query = DAOUtil
                .initQuery(Department.class, session, entityRequest);
        return query.getResultList();
    }

    @Override
    public Optional<Department> findCurrentDepartmentById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();

        org.hibernate.query.Query<Department> departmentQuery;

        departmentQuery = session.createQuery("FROM Department WHERE id = :departmentId", Department.class);
        departmentQuery.setParameter("departmentId", id);

        return Optional.ofNullable(departmentQuery.uniqueResult());
    }

    @Override
    public Department initDepartmentChanges(Department department) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.merge(department);
    }

    @Override
    public void deleteCurrentDepartment(Department department) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(
                "UPDATE Employee SET depId = null WHERE depId = :id"
        );

        query.setParameter("id", department.getId());

        query.executeUpdate();

        session.remove(department);
    }
}
