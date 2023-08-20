package com.techer.flightsearchapi.service;

import com.techer.flightsearchapi.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {


    Airport addAirport(Airport airportDto);

    List<Airport> getAllAirport();

    Airport getAirportByAirportCode(String airportCode);

    List<Airport> getAirportByCity(String city);

    Airport updateAirportByAirportCode(String airportCode, Airport airport);

    void deleteAirportByAirportCode(String airportCode);
}
