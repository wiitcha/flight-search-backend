package com.amadeus.flightsearch.repository;

import com.amadeus.flightsearch.entity.Airport;
import com.amadeus.flightsearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findFlightById(Long id);
    boolean existsByDepartureAirportAndArrivalAirportAndDepartureTimeAndArrivalTime(Airport departureAirport, Airport arrivalAirport, LocalTime departureTime, LocalTime arrivalTime);
}