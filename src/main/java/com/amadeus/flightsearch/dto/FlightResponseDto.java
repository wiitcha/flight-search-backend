package com.amadeus.flightsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FlightResponseDto(Long id, String departureAirport, String arrivalAirport, LocalTime departureTime, LocalTime arrivalTime) {
}
