package com.techer.flightsearchapi.service;

import com.techer.flightsearchapi.entity.Airport;
import com.techer.flightsearchapi.exception.AirportNotFoundException;
import com.techer.flightsearchapi.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService{

    private final AirportRepository airportRepository;


    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;

    }

    @Override
    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public List<Airport> getAllAirport() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirportByAirportCode(String airportCode) {
        return airportRepository.findByAirportCode(airportCode).orElseThrow(() -> new AirportNotFoundException("Airport not found with given airport code: " + airportCode));
    }

    @Override
    public List<Airport> getAirportByCity(String city) {
        return airportRepository.findAllByCity(city);
    }

    @Override
    public Airport updateAirportByAirportCode(String airportCode,Airport airport) {
        Airport foundAirport=getAirportByAirportCode(airportCode);
        foundAirport.setCity(airport.getCity());
       return airportRepository.save(airport);

    }

    @Override
    public void deleteAirportByAirportCode(String airportCode) {
        airportRepository.deleteById(airportCode);
    }
}
