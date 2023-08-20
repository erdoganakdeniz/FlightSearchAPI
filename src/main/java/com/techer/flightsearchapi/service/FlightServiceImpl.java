package com.techer.flightsearchapi.service;

import com.techer.flightsearchapi.dto.FlightRequest;
import com.techer.flightsearchapi.dto.FlightResponse;
import com.techer.flightsearchapi.entity.Flight;
import com.techer.flightsearchapi.exception.FlightNotFoundException;
import com.techer.flightsearchapi.exception.InvalidFlightDateException;
import com.techer.flightsearchapi.mapper.FlightDataMapper;
import com.techer.flightsearchapi.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final FlightDataMapper flightDataMapper;

    public FlightServiceImpl(FlightRepository flightRepository, AirportService airportService, FlightDataMapper flightDataMapper) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
        this.flightDataMapper = flightDataMapper;
    }

    @Override
    public Flight createFlight(FlightRequest flightRequest) {

        return flightRepository.save(flightDataMapper.flightRequestToFlight(flightRequest));
    }

    @Override
    public List<FlightResponse> searchFlights(String departureAirport,
                                              String arrivalAirport,
                                              LocalDateTime departureDateTime,
                                              LocalDateTime returnDateTime) {

        if (returnDateTime == null) {
            return searchOneWayFlights(departureAirport,
                    arrivalAirport,
                    departureDateTime);
        } else {
            return searchRoundTripFlights(departureAirport,
                    arrivalAirport,
                    departureDateTime,
                    returnDateTime);

        }
    }

    @Override
    public Flight updateFlightByFlightId(UUID flightId, FlightRequest flightRequest) {
        Flight foundFlight = getFlightByFlightId(flightId);
        foundFlight.setArrivalAirport(airportService.getAirportByAirportCode(flightRequest.arrivalAirportCode()));
        foundFlight.setDepartureAirport(airportService.getAirportByAirportCode(flightRequest.departureAirportCode()));
        foundFlight.setDepartureDateTime(flightRequest.departureDateTime());
        foundFlight.setArrivalDateTime(flightRequest.arrivalDateTime());
        foundFlight.setPrice(flightRequest.price());
        return flightRepository.save(foundFlight);

    }

    @Override
    public Flight getFlightByFlightId(UUID flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with given id: " + flightId));
    }

    @Override
    public void deleteFlightByFlightId(UUID flightId) {
        Flight flight = getFlightByFlightId(flightId);
        flightRepository.deleteById(flight.getId());
    }

    private List<FlightResponse> searchOneWayFlights(String departureAirport,
                                                     String arrivalAirport,
                                                     LocalDateTime departureDateTime) {
        List<Flight> departureFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(airportService.getAirportByAirportCode(departureAirport), airportService.getAirportByAirportCode(arrivalAirport), departureDateTime);
        return Collections.singletonList(new FlightResponse(departureFlights, null));
    }

    private List<FlightResponse> searchRoundTripFlights(String departureAirport,
                                                        String arrivalAirport,
                                                        LocalDateTime departureDateTime,
                                                        LocalDateTime returnDateTime) {

        if (returnDateTime.isBefore(departureDateTime)) {
            throw new InvalidFlightDateException("Return date cannot be before the departure date.");
        }
        List<Flight> departureFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(airportService.getAirportByAirportCode(departureAirport), airportService.getAirportByAirportCode(arrivalAirport), departureDateTime);
        List<Flight> returnFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(airportService.getAirportByAirportCode(arrivalAirport), airportService.getAirportByAirportCode(departureAirport), returnDateTime);
        return Collections.singletonList(new FlightResponse(departureFlights, returnFlights));
    }
}
