package com.amadeus.flightsearch.service;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.FlightResponseDto;
import com.amadeus.flightsearch.entity.Flight;

import java.util.List;

public interface FlightService {

    void deleteFlight(Long id);
    void updateFlight(Long id, FlightDto flightDto);
    List<FlightResponseDto> getAllFlights();
    Flight bookFlight(FlightDto flightDto);
    Flight getFlight(Long id);
}
