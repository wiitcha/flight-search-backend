package com.amadeus.flightsearch.repository;

import com.amadeus.flightsearch.entity.Airport;
import com.amadeus.flightsearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findFlightById(Long id);
    boolean existsByDepartureAirportAndArrivalAirportAndDepartureTimeAndReturnTimeAndDepartureDateAndReturnDate(Airport departureAirport, Airport arrivalAirport, LocalTime departureTime, LocalTime returnTime, LocalDate departureDate, LocalDate returnDate);
}