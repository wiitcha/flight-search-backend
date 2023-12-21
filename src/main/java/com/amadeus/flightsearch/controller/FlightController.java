package com.amadeus.flightsearch.controller;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.FlightResponseDto;
import com.amadeus.flightsearch.entity.Flight;
import com.amadeus.flightsearch.service.FlightService;
import com.amadeus.flightsearch.util.converter.FlightConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<Void> bookFlight(@RequestBody FlightDto flightDto) {
        flightService.bookFlight(flightDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getFlight(@PathVariable Long id) {
        Flight flight = flightService.getFlight(id);
        return ResponseEntity.ok(FlightConverter.toResponseDto(flight));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFlight(@PathVariable Long id, @RequestBody FlightDto flightDto) {
        flightService.updateFlight(id, flightDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok().build();
    }




}
