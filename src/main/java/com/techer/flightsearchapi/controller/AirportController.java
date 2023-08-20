package com.techer.flightsearchapi.controller;

import com.techer.flightsearchapi.entity.Airport;
import com.techer.flightsearchapi.service.AirportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Airport API")
@RequestMapping("/api/v1/airport")
@RestController
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAirport(){
        return ResponseEntity.ok(airportService.getAllAirport());
    }

    @PostMapping
    public ResponseEntity<?> addAirport(@RequestBody Airport airport){
        return ResponseEntity.ok(airportService.addAirport(airport));
    }

    @PutMapping
    public ResponseEntity<?> updateAirportByAirportCode(@RequestParam String airportCode,@RequestBody Airport airport){
        return ResponseEntity.ok(airportService.updateAirportByAirportCode(airportCode,airport));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAirportByAirportCode(@RequestParam String airportCode){
        airportService.deleteAirportByAirportCode(airportCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
