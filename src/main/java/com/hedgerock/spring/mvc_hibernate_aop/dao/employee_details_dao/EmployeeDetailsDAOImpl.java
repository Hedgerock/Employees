package com.hedgerock.spring.mvc_hibernate_aop.dao.employee_details_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import jakarta.persistence.EntityGraph;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeDetailsDAOImpl implements EmployeeDetailsDAO {

    private final SessionFactory sessionFactory;

    public EmployeeDetailsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
