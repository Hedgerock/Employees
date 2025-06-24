package com.hedgerock.spring.mvc_hibernate_aop.dao.nationality_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NationalityDAOImpl implements NationalityDAO {

    private final SessionFactory sessionFactory;

    public NationalityDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Nationality> getNationalities() {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Nationality> nationalityQuery = session.createQuery("FROM Nationality", Nationality.class);

        return nationalityQuery.getResultList();
    }

    @Override
    public Nationality saveNationality(Nationality nationality) {
        final Session session = this.sessionFactory.getCurrentSession();
        return session.merge(nationality);
    }

    @Override
    public Optional<Nationality> findNationality(Long natId) {
        final Session session = this.sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Nationality.class, natId));
    }

    @Override
    public Optional<Nationality> findNationality(String natName) {
        final Session session = this.sessionFactory.getCurrentSession();
        final Query<Nationality> nationalityQuery = session.createQuery("FROM Nationality WHERE name = :name", Nationality.class);
        nationalityQuery.setParameter("name", natName);
        return Optional.ofNullable(nationalityQuery.uniqueResult());
    }

    @Override
    public void deleteNationality(Long natId) {
        final Session session = this.sessionFactory.getCurrentSession();

        Nationality nationality = session.find(Nationality.class, natId);

        System.out.println(nationality);

        if (nationality == null) {
            return;
        }

        Query query = session.createQuery("UPDATE Employee SET nationalityId = NULL WHERE nationality.id = :id");
        query.setParameter("id", natId);
        query.executeUpdate();

        session.remove(nationality);
    }
}
