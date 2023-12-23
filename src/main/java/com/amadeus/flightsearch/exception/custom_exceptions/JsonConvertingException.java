package com.amadeus.flightsearch.exception.custom_exceptions;

public class JsonConvertingException extends RuntimeException{
    public JsonConvertingException() {
        super("Object could not be converted to JSON");
    }
}
