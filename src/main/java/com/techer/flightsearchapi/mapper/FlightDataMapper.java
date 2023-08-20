package com.techer.flightsearchapi.mapper;

import com.techer.flightsearchapi.dto.FlightRequest;
import com.techer.flightsearchapi.entity.Flight;
import com.techer.flightsearchapi.service.AirportService;
import org.springframework.stereotype.Component;

@Component
public class FlightDataMapper {

    private final AirportService airportService;

    public FlightDataMapper(AirportService airportService) {
        this.airportService = airportService;
    }

    public Flight flightRequestToFlight(FlightRequest flightRequest) {
        return new Flight.Builder().departureDateTime(flightRequest.departureDateTime())
                .arrivalDateTime(flightRequest.arrivalDateTime())
                .departureAirport(airportService.getAirportByAirportCode(flightRequest.departureAirportCode()))
                .arrivalAirport(airportService.getAirportByAirportCode(flightRequest.arrivalAirportCode()))
                .price(flightRequest.price()).build();
    }
}
