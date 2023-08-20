package com.techer.flightsearchapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightRequest(LocalDateTime departureDateTime,
                            LocalDateTime arrivalDateTime,
                            BigDecimal price,
                            String departureAirportCode,
                            String arrivalAirportCode) {
}
