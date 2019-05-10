package com.revature.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FLIGHTS")
@SequenceGenerator(name = "flight_seq", sequenceName = "FLIGHT_PK_SEQ", allocationSize = 1)
public class Flight {

    @Id
    @Column(name = "flight_number", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_seq")
    private int flightNumber;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departureTime")
    private Time departureTime;

    @Column(name = "arrivalTime")
    private Time arrivalTime;

    @OneToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane_id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "flight_number"))
    private List<User> users;

    public Flight() {
    }

    public Flight(String origin, String destination, Time departureTime, Time arrivalTime, Airplane airplane_id, List<User> passengers) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airplane_id = airplane_id;
        this.users = users;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Airplane getAirplane_id() {
        return airplane_id;
    }

    public void setAirplane_id(Airplane airplane_id) {
        this.airplane_id = airplane_id;
    }

    public List<User> getPassengers() {
        return users;
    }

    public void setPassengers(List<User> passengers) {
        this.users = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return flightNumber == flight.flightNumber &&
                Objects.equals(origin, flight.origin) &&
                Objects.equals(destination, flight.destination) &&
                Objects.equals(departureTime, flight.departureTime) &&
                Objects.equals(arrivalTime, flight.arrivalTime) &&
                Objects.equals(airplane_id, flight.airplane_id) &&
                Objects.equals(users, flight.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, origin, destination, departureTime, arrivalTime, airplane_id, users);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", airplane_id=" + airplane_id +
                ", passengers=" + users +
                '}';
    }
}
