package com.techer.flightsearchapi.scheduler;

import com.google.gson.*;
import com.techer.flightsearchapi.entity.Airport;
import com.techer.flightsearchapi.entity.Flight;
import com.techer.flightsearchapi.repository.AirportRepository;
import com.techer.flightsearchapi.repository.FlightRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;


@Component
public class ScheduledJob {
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public ScheduledJob(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void updateFlightData() {

        try {
            JsonParser parser = new JsonParser();
            JsonArray airportObjects = (JsonArray) parser.parse(new FileReader("src/main/resources/airports.json"));
            JsonArray flightObjects = (JsonArray) parser.parse(new FileReader("src/main/resources/flights.json"));

            for (JsonElement airportElement : airportObjects) {
                JsonObject airportObject = airportElement.getAsJsonObject();
                String airportCode = airportObject.get("airportCode").getAsString();
                String city = airportObject.get("city").getAsString();
                Airport departureAirport = new Airport();
                departureAirport.setAirportCode(airportCode);
                departureAirport.setCity(city);
                airportRepository.save(departureAirport);
            }
            for (JsonElement flightElement : flightObjects) {
                JsonObject flightObject = flightElement.getAsJsonObject();

                Airport departureAirportObject = getRandomAirportFromRepository();
                Airport arrivalAirportObject = getRandomAirportFromRepository();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime departureDateTime = LocalDateTime.parse(flightObject.get("departureDateTime").getAsString(), formatter);
                LocalDateTime arrivalDateTime = LocalDateTime.parse(flightObject.get("arrivalDateTime").getAsString(), formatter);
                BigDecimal price = flightObject.get("price").getAsBigDecimal();

                Flight flight = new Flight();
                flight.setDepartureAirport(departureAirportObject);
                flight.setArrivalAirport(arrivalAirportObject);
                flight.setDepartureDateTime(departureDateTime);
                flight.setArrivalDateTime(arrivalDateTime);
                flight.setPrice(price);
                flightRepository.save(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Airport getRandomAirportFromRepository() {
        List<Airport> airportList = airportRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(airportList.size());
        return airportList.get(randomIndex);
    }
}


