package com.amadeus.flightsearch.repository;

import com.amadeus.flightsearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}