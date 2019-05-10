package com.revature.repos;


import com.revature.models.Airplane;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirplaneRepo {

    private SessionFactory factory;
    Logger log = Logger.getLogger(FlightRepo.class);

    @Autowired
    public AirplaneRepo(SessionFactory factory) {
        this.factory = factory;
    }

    public List<Airplane> getAll() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from Airplanes", Airplane.class).getResultList();
    }

    public Airplane getByType(String type) {
        Session session = factory.getCurrentSession();
        return session.get(Airplane.class, type);
    }

    public Airplane getById(int airplane_id) {
        Session session = factory.getCurrentSession();
        return session.get(Airplane.class, airplane_id);
    }
}
