package com.amadeus.flightsearch.dto;

import com.amadeus.flightsearch.entity.Flight;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * DTO for {@link Flight}
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class FlightDto implements Serializable {
    Long id;
    AirportDto departureAirport;
    AirportDto arrivalAirport;
    LocalTime departureTime;
    LocalTime arrivalTime;
}