package com.amadeus.flightsearch.dto;

import com.amadeus.flightsearch.entity.Flight;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * DTO for {@link Flight}
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FlightDto(Long id, Long departureAirport, Long arrivalAirport, LocalTime departureTime, LocalTime arrivalTime) implements Serializable {
}