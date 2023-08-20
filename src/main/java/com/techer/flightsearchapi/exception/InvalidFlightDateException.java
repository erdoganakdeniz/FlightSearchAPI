package com.techer.flightsearchapi.exception;

public class InvalidFlightDateException extends RuntimeException{
    public InvalidFlightDateException(String message) {
        super(message);
    }
}
