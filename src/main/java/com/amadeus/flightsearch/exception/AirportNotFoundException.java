package com.amadeus.flightsearch.exception;

public class AirportNotFoundException extends RuntimeException{
    public AirportNotFoundException(Long id) {
        super("Airport not found - " + id);
    }

    public AirportNotFoundException(String message) {
        super(message);
    }
}
