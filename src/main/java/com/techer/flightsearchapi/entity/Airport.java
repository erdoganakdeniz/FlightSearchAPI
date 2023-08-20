package com.techer.flightsearchapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    private String airportCode;

    @Column(name = "city")
    private String city;

    public Airport() {
    }

    public Airport(String airportCode) {
        this.airportCode = airportCode;
    }

    public Airport(String airportCode, String city) {
        this.airportCode = airportCode;
        this.city = city;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
