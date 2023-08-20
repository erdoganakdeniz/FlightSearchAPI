package com.techer.flightsearchapi.repository;

import com.techer.flightsearchapi.entity.Airport;
import com.techer.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AirportRepository extends JpaRepository<Airport, String> {

    Optional<Airport> findByAirportCode(String airportCode);

    List<Airport> findAllByCity(String city);


}
