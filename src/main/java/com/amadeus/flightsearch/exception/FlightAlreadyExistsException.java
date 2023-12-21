package com.amadeus.flightsearch.exception;

public class FlightAlreadyExistsException extends RuntimeException{
    public FlightAlreadyExistsException() {
        super("Flight already exists");
    }
}
