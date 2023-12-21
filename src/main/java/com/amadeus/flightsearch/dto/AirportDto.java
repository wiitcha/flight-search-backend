package com.amadeus.flightsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;


import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.amadeus.flightsearch.entity.Airport}
 */

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AirportDto(Long id, String city,List<FlightDto> departureFlightsList, List<FlightDto> arrivalFlightsList) implements Serializable {

}