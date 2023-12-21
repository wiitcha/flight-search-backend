package com.amadeus.flightsearch.dto;

import com.amadeus.flightsearch.entity.Flight;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO for {@link Flight}
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FlightDto(Long id, Long departureAirport, Long arrivalAirport, LocalDate departureDate, LocalTime departureTime, LocalDate returnDate, LocalTime returnTime, BigDecimal price) implements Serializable {
}