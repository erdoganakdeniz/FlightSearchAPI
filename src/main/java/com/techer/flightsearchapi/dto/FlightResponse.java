package com.techer.flightsearchapi.dto;

import com.techer.flightsearchapi.entity.Flight;

import java.util.List;

public record FlightResponse(List<Flight> departureFlights, List<Flight> returnFlights) {
}
