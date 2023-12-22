package com.amadeus.flightsearch.controller;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.FlightResponseDto;
import com.amadeus.flightsearch.service.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flights")
@SecurityRequirement(name = "bearerAuth")
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
        return ResponseEntity.ok(flightService.getFlight(id));
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

    @GetMapping("/search")
    public ResponseEntity<List<FlightResponseDto>> retrieveFlights(@RequestBody FlightDto flightDto) {
        return ResponseEntity.ok(flightService.searchFlights(flightDto));
    }
}
