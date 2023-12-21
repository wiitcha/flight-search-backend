package com.amadeus.flightsearch.service;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.FlightResponseDto;
import com.amadeus.flightsearch.entity.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    void deleteFlight(Long id);
    void updateFlight(Long id, FlightDto flightDto);
    List<FlightResponseDto> getAllFlights();
    Flight bookFlight(FlightDto flightDto);
    FlightResponseDto getFlight(Long id);
    List<FlightResponseDto> searchFlights(Long departureAirport, Long arrivalAirport, LocalDate departureDate, LocalDate returnDate);
}
