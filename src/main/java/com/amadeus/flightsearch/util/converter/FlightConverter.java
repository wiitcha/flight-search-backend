package com.amadeus.flightsearch.util.converter;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.FlightResponseDto;
import com.amadeus.flightsearch.entity.Airport;
import com.amadeus.flightsearch.entity.Flight;
import com.amadeus.flightsearch.exception.AirportNotFoundException;
import com.amadeus.flightsearch.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FlightConverter {

    private final AirportRepository airportRepository;

    public Flight toEntity(FlightDto flightDto) {
        Optional<Airport> departureAirport = airportRepository.findAirportById(flightDto.departureAirport());
        Optional<Airport> arrivalAirport = airportRepository.findAirportById(flightDto.arrivalAirport());

        if (departureAirport.isPresent() && arrivalAirport.isPresent()) {
            return Flight.builder()
                    .departureAirport(departureAirport.get())
                    .arrivalAirport(arrivalAirport.get())
                    .departureTime(flightDto.departureTime())
                    .arrivalTime(flightDto.arrivalTime())
                    .build();
        }
        throw new AirportNotFoundException("Invalid airport credentials");
    }

    public static FlightDto toDto(Flight flight) {
        return FlightDto.builder()
                .departureAirport(flight.getDepartureAirport().getId())
                .arrivalAirport(flight.getArrivalAirport().getId())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .build();
    }

    public static FlightResponseDto toResponseDto(Flight flight) {
        return FlightResponseDto.builder()
                .departureAirport(flight.getDepartureAirport().getCity())
                .arrivalAirport(flight.getArrivalAirport().getCity())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .build();
    }
}
