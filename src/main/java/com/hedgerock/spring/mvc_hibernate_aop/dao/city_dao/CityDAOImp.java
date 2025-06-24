package com.hedgerock.spring.mvc_hibernate_aop.dao.city_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CityDAOImp implements CityDAO {

    private final SessionFactory sessionFactory;

    public CityDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<City> getCities() {
        Session session = this.sessionFactory.getCurrentSession();

        Query<City> cityQuery = session.createQuery("FROM City", City.class);

        return cityQuery.getResultList();
    }

    @Override
    public City saveCity(City city) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.merge(city);
    }

    @Override
    public Optional<City> findCity(Long cityId) {
        Session session = this.sessionFactory.getCurrentSession();

        City city = session.find(City.class, cityId);

        return Optional.ofNullable(city);
    }

    @Override
    public Optional<City> findCurrentCity(String cityName) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<City> query = session.createQuery("FROM City WHERE name = :name", City.class);
        query.setParameter("name", cityName);

        return query.uniqueResultOptional();
    }

    @Override
    public void deleteCity(Long cityId) {
        Session session = this.sessionFactory.getCurrentSession();

        City city = session.find(City.class, cityId);

        if (city == null) {
            return;
        }

        Query query = session.createQuery("UPDATE Employee SET cityId = NULL WHERE city.id = :id");
        query.setParameter("id", cityId);
        query.executeUpdate();

        session.remove(city);
    }
}
