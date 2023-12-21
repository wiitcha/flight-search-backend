package com.amadeus.flightsearch.util.converter;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.FlightResponseDto;
import com.amadeus.flightsearch.entity.Airport;
import com.amadeus.flightsearch.entity.Flight;
import com.amadeus.flightsearch.exception.AirportNotFoundException;
import com.amadeus.flightsearch.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class FlightConverter {

    private final AirportService airportService;

    public Flight toEntity(FlightDto flightDto) {
        Airport departureAirport = airportService.getAirport(flightDto.departureAirport());
        Airport arrivalAirport = airportService.getAirport(flightDto.arrivalAirport());

        if (departureAirport != null && arrivalAirport != null) {
            return Flight.builder()
                    .departureAirport(departureAirport)
                    .arrivalAirport(arrivalAirport)
                    .departureDate(flightDto.departureDate())
                    .departureTime(flightDto.departureTime())
                    .returnDate(flightDto.returnDate())
                    .returnTime(flightDto.returnTime())
                    .build();
        }
        throw new AirportNotFoundException("Invalid airport credentials");
    }

    public static FlightDto toDto(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport().getId())
                .arrivalAirport(flight.getArrivalAirport().getId())
                .departureDate(flight.getDepartureDate())
                .departureTime(flight.getDepartureTime())
                .returnDate(flight.getReturnDate())
                .returnTime(flight.getReturnTime())
                .build();
    }

    public static FlightResponseDto toResponseDto(Flight flight) {
        return FlightResponseDto.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport().getCity())
                .arrivalAirport(flight.getArrivalAirport().getCity())
                .departureDate(flight.getDepartureDate())
                .departureTime(flight.getDepartureTime())
                .returnDate(flight.getReturnDate())
                .returnTime(flight.getReturnTime())
                .build();
    }
}
