package com.techer.flightsearchapi.service;

import com.techer.flightsearchapi.dto.FlightRequest;
import com.techer.flightsearchapi.dto.FlightResponse;
import com.techer.flightsearchapi.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FlightService {

    Flight createFlight(FlightRequest flightRequest);

    List<FlightResponse> searchFlights(String departureAirport,
                                       String arrivalAirport,
                                       LocalDateTime departureDateTime,
                                       LocalDateTime returnDateTime);

    Flight updateFlightByFlightId(UUID flightId, FlightRequest flightRequest);

    Flight getFlightByFlightId(UUID flightId);

    void deleteFlightByFlightId(UUID flightId);
}
