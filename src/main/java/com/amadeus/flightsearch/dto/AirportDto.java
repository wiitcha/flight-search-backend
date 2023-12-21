package com.amadeus.flightsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.amadeus.flightsearch.entity.Airport}
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class AirportDto implements Serializable {
    Long id;
    String city;
    List<FlightDto> departureFlightsList;
    List<FlightDto> arrivalFlightsList;
}