package com.amadeus.flightsearch.exception.custom_exceptions;

public class FlightAlreadyExistsException extends RuntimeException{
    public FlightAlreadyExistsException() {
        super("Flight already exists");
    }
}
