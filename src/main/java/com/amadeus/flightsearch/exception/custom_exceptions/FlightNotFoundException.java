package com.amadeus.flightsearch.exception.custom_exceptions;

public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(Long id) {
        super("Flight not found | ID: " + id);
    }
}
