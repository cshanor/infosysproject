package com.revature.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "AIRPLANES")
@SequenceGenerator(name = "airplane_seq", sequenceName = "AIRPLANE_PK_SEQ", allocationSize = 1)
public class Airplane {

    @Id
    @Column(name = "airplane_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airplane_seq")
    private int airplane_id;

    @Column(name = "airplaneType", unique = true)
    private String airplaneType;

    @Column(name = "airplaneSeats")
    private int airplaneSeats;

    @OneToMany
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "flight_number"))
    private List<User> users;

    public Airplane(String airplaneType, int airplaneSeats) {
        this.airplaneType = airplaneType;
        this.airplaneSeats = airplaneSeats;
    }

    public int getAirplane_id() {
        return airplane_id;
    }

    public void setAirplane_id(int airplane_id) {
        this.airplane_id = airplane_id;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public int getAirplaneSeats() {
        return airplaneSeats;
    }

    public void setAirplaneSeats(int airplaneSeats) {
        this.airplaneSeats = airplaneSeats;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return airplane_id == airplane.airplane_id &&
                airplaneSeats == airplane.airplaneSeats &&
                Objects.equals(airplaneType, airplane.airplaneType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airplane_id, airplaneType, airplaneSeats);
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "airplane_id=" + airplane_id +
                ", airplaneType='" + airplaneType + '\'' +
                ", airplaneSeats=" + airplaneSeats +
                '}';
    }
}
