package com.techer.flightsearchapi.controller;

import com.techer.flightsearchapi.dto.FlightRequest;
import com.techer.flightsearchapi.dto.FlightResponse;
import com.techer.flightsearchapi.entity.Flight;
import com.techer.flightsearchapi.service.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Flight API")
@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody FlightRequest flightRequest) {
        Flight flightResponse = flightService.createFlight(flightRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(@RequestParam String departureAirport,
                                                              @RequestParam String arrivalAirport,
                                                              @RequestParam LocalDate departureDate,
                                                              @RequestParam(required = false) LocalDate returnDate) {
        LocalDateTime departureDateTime = departureDate.atStartOfDay();
        LocalDateTime returnDateTime = null;
        if (returnDate != null) {
            returnDateTime = returnDate.atStartOfDay();
        }

        return ResponseEntity.ok(flightService.searchFlights(departureAirport, arrivalAirport, departureDateTime, returnDateTime));
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightByFlightId(@PathVariable UUID flightId) {
        return ResponseEntity.ok(flightService.getFlightByFlightId(flightId));
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlightByFlightId(@PathVariable UUID flightId, @RequestBody FlightRequest flightRequest) {
        return ResponseEntity.ok(flightService.updateFlightByFlightId(flightId, flightRequest));
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlightByFlightId(@PathVariable UUID flightId) {
        flightService.deleteFlightByFlightId(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
