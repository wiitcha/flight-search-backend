package com.amadeus.flightsearch.repository;

import com.amadeus.flightsearch.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findAirportById(Long id);

    boolean existsAirportByCity(String city);


}
