package com.revature.repos;

import com.revature.models.Flight;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class FlightRepo {

    private SessionFactory factory;
    Logger log = Logger.getLogger(FlightRepo.class);

    @Autowired
    public FlightRepo(SessionFactory factory) {
        this.factory = factory;
    }

    public List<Flight> getAll() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from Flights", Flight.class).getResultList();
    }

    public Flight getByFlightNumber(int flightNumber) {
        Session session = factory.getCurrentSession();
        return session.get(Flight.class, flightNumber);
    }

    public Flight getByOrigin(String origin) {
        Session session = factory.getCurrentSession();
        return session.get(Flight.class, origin);
    }

    public Flight getByDestination(String destination) {
        Session session = factory.getCurrentSession();
        return session.get(Flight.class, destination);
    }
}
