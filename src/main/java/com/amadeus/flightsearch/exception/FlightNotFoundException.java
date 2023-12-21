package com.amadeus.flightsearch.exception;

public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(Long id) {
        super("Flight not found | ID: " + id);
    }
}
