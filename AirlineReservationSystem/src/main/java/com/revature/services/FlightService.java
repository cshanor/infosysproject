package com.revature.services;


import com.revature.models.Flight;
import com.revature.repos.FlightRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    private FlightRepo flightRepo;
    private Logger log = Logger.getLogger(FlightService.class);

    ArrayList seats = new ArrayList();

    @Autowired
    public FlightService(FlightRepo FlightRepo) {
        flightRepo = FlightRepo;
    }

    public FlightService() {
        super();
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public List<Flight> getAll() {
        return flightRepo.getAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Flight getByFlightNumber(int flightNumber) {
        return flightRepo.getByFlightNumber(flightNumber);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Flight getByOrigin(String origin) {
        return flightRepo.getByOrigin(origin);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Flight getByDestination(String destination) {
        return flightRepo.getByDestination(destination);
    }
}
