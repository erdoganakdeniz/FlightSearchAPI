package com.techer.flightsearchapi.repository;

import com.techer.flightsearchapi.entity.Airport;
import com.techer.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {


    @Query("SELECT f FROM Flight f " +
            "WHERE f.departureAirport = :departureAirport " +
            "AND f.arrivalAirport = :arrivalAirport " +
            "AND DATE(f.departureDateTime) = DATE(:departureDateTime)")
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTime(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime);


}
