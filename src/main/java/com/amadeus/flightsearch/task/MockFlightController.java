package com.amadeus.flightsearch.task;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock-api/v1/flights")
public class MockFlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<Void> bookFlights(@RequestBody FlightDto flightDto) {
        flightService.bookFlight(flightDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
