package com.amadeus.flightsearch.exception;

public class AirportAlreadyExistsException extends RuntimeException{
    public AirportAlreadyExistsException() {
        super("Airport already exists");
    }
}
