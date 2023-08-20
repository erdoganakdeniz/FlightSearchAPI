package com.techer.flightsearchapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    public Flight() {
    }

    private Flight(Builder builder) {
        setId(builder.id);
        setDepartureDateTime(builder.departureDateTime);
        setArrivalDateTime(builder.arrivalDateTime);
        setPrice(builder.price);
        setDepartureAirport(builder.departureAirport);
        setArrivalAirport(builder.arrivalAirport);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public static final class Builder {
        private UUID id;
        private LocalDateTime departureDateTime;
        private LocalDateTime arrivalDateTime;
        private BigDecimal price;
        private Airport departureAirport;
        private Airport arrivalAirport;

        public Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder departureDateTime(LocalDateTime val) {
            departureDateTime = val;
            return this;
        }

        public Builder arrivalDateTime(LocalDateTime val) {
            arrivalDateTime = val;
            return this;
        }

        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder departureAirport(Airport val) {
            departureAirport = val;
            return this;
        }

        public Builder arrivalAirport(Airport val) {
            arrivalAirport = val;
            return this;
        }

        public Flight build() {
            return new Flight(this);
        }
    }
}
