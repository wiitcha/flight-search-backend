package com.amadeus.flightsearch.service;

import com.amadeus.flightsearch.dto.AirportDto;
import com.amadeus.flightsearch.entity.Airport;

import java.util.List;

public interface AirportService {

    void deleteAirport(Long id);
    void updateAirport(Long id, AirportDto airportDto);
    List<Airport> findAllAirports();

    Airport saveAirport(AirportDto airportDto);



}
