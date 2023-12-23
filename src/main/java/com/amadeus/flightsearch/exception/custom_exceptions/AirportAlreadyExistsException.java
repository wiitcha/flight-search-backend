package com.amadeus.flightsearch.exception.custom_exceptions;

public class AirportAlreadyExistsException extends RuntimeException{
    public AirportAlreadyExistsException(String message) {
        super(message);
    }
}
