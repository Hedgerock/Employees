package com.hedgerock.spring.mvc_hibernate_aop.dao.employee_details_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
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

        EntityGraph<EmployeeDetails> graph = session.createEntityGraph(EmployeeDetails.class);
        graph.addAttributeNodes("employee", "picture", "socialMedia", "employeeDescription");
        graph.addSubgraph("emails");
        graph.addSubgraph("phoneNumbers");

        Map<String, Object>properties = new HashMap<>();

        properties.put("jakarta.persistence.fetchGraph", graph);

        return Optional.ofNullable(session.find(EmployeeDetails.class, id, properties));
    }

    @Override
    public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
        final Session session = this.sessionFactory.getCurrentSession();

        return session.merge(employeeDetails);
    }
}
