package com.amadeus.flightsearch.util.converter;

import com.amadeus.flightsearch.dto.AirportDto;
import com.amadeus.flightsearch.entity.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportConverter {

    public Airport toEntity(AirportDto airportDto) {
        return Airport.builder()
                .city(airportDto.getCity())
                .build();
    }

    public static AirportDto toDTO(Airport airport) {
        return AirportDto.builder()
                .city(airport.getCity())
                .build();
    }

}
