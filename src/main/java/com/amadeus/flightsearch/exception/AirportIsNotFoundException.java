package com.amadeus.flightsearch.exception;

public class AirportIsNotFoundException extends RuntimeException{
    public AirportIsNotFoundException() {
        super("Airport is not found");
    }
}
