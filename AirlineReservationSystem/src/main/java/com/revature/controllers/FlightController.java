package com.revature.controllers;

import com.revature.models.Flight;
import com.revature.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/flight")
public class FlightController {

    private FlightService service;

    @Autowired
    public FlightController(FlightService flightService) {
        this.service = flightService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flight GetFlight(@RequestBody int flightNumber, HttpServletResponse resp) {
        Flight getFlight = service.getByFlightNumber(flightNumber);
        resp.addHeader("flight number", String.valueOf(getFlight.getFlightNumber()));
        resp.addHeader("origin", getFlight.getOrigin().toString());
        resp.addHeader("destination", getFlight.getDestination().toString());
        resp.addHeader("depart_time", getFlight.getDepartureTime().toString());
        resp.addHeader("arrival_time", getFlight.getArrivalTime().toString());
        resp.addHeader("airplane_id", getFlight.getAirplane_id().toString());
        return getFlight;
    }
}
