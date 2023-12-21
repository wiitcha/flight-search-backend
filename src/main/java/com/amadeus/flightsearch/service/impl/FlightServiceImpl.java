package com.amadeus.flightsearch.service.impl;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.FlightResponseDto;
import com.amadeus.flightsearch.entity.Airport;
import com.amadeus.flightsearch.entity.Flight;
import com.amadeus.flightsearch.exception.FlightAlreadyExistsException;
import com.amadeus.flightsearch.exception.FlightNotFoundException;
import com.amadeus.flightsearch.repository.AirportRepository;
import com.amadeus.flightsearch.repository.FlightRepository;
import com.amadeus.flightsearch.service.FlightService;
import com.amadeus.flightsearch.util.converter.FlightConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightConverter flightConverter;
    private final AirportRepository airportRepository;

    @Override
    public void deleteFlight(Long id) {
        Optional<Flight> flight = flightRepository.findFlightById(id);
        flight.ifPresentOrElse(flightRepository::delete,
                () -> {
                    throw new FlightNotFoundException(id);
                }
                );
    }

    @Override
    public void updateFlight(Long id, FlightDto flightDto) {
        Flight existingFlight = flightRepository.findFlightById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));

        Flight updatedFlight = flightConverter.toEntity(flightDto);
        updatedFlight.setId(existingFlight.getId());
        flightRepository.save(updatedFlight);
    }

    @Override
    public List<FlightResponseDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(FlightConverter::toResponseDto)
                .toList();
    }

    @Override
    public Flight bookFlight(FlightDto flightDto) {
        Optional<Airport> departureAirport = airportRepository.findAirportById(flightDto.departureAirport());
        Optional<Airport> arrivalAirport = airportRepository.findAirportById(flightDto.arrivalAirport());

        if (departureAirport.isPresent() && arrivalAirport.isPresent() &&
                (!flightRepository.existsByDepartureAirportAndArrivalAirportAndDepartureTimeAndArrivalTime(
                    departureAirport.get(),
                    arrivalAirport.get(),
                    flightDto.departureTime(),
                    flightDto.arrivalTime()
            ))) {
                Flight flight = flightConverter.toEntity(flightDto);
                flightRepository.save(flight);
                return flight;

        }
        throw new FlightAlreadyExistsException();
    }

    @Override
    public Flight getFlight(Long id) {
        return flightRepository.findFlightById(id).orElseThrow(() -> new FlightNotFoundException(id));
    }
}
