package com.hedgerock.spring.mvc_hibernate_aop.dao.employee_details_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil.getEntityName;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.QueryTemplates.*;

@Repository
public class EmployeeDetailsDAOImpl implements EmployeeDetailsDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Optional<EmployeeDetails> getEmployeeDetails(Long id) {
        final Session session = this.sessionFactory.getCurrentSession();

        String entityName = getEntityName(TEMPLATE_OF_ROOT_QUERY,
                "EmployeeDetails", "id", ":employeeDetailsId"
        );

        try {
            Query<EmployeeDetails> query = DAOUtil.initQuery(
                    EmployeeDetails.class,
                    session,
                    entityName
            );

            query.setParameter("employeeDetailsId", id);

            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    @Override
    public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
        final Session session = this.sessionFactory.getCurrentSession();

        return session.merge(employeeDetails);
    }
}
