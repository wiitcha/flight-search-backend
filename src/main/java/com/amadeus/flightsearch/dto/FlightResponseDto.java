package com.amadeus.flightsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FlightResponseDto(Long id, String departureAirport, String arrivalAirport, LocalDate departureDate, LocalTime departureTime, LocalDate returnDate, LocalTime returnTime, BigDecimal price) {
}
