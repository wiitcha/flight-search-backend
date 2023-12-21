package com.amadeus.flightsearch.service.impl;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.dto.FlightResponseDto;
import com.amadeus.flightsearch.entity.Airport;
import com.amadeus.flightsearch.entity.Flight;
import com.amadeus.flightsearch.exception.FlightAlreadyExistsException;
import com.amadeus.flightsearch.exception.FlightNotFoundException;
import com.amadeus.flightsearch.repository.FlightRepository;
import com.amadeus.flightsearch.service.AirportService;
import com.amadeus.flightsearch.service.FlightService;
import com.amadeus.flightsearch.util.converter.FlightConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final FlightConverter flightConverter;
    private final EntityManager entityManager;

    @Override
    public void deleteFlight(Long id) {
        Optional<Flight> flight = flightRepository.findFlightById(id);
        flight.ifPresentOrElse(flightRepository::delete,
                () -> {
                    throw new FlightNotFoundException(id);
                }
                );
    }

    @Override
    public void updateFlight(Long id, FlightDto flightDto) {
        Flight existingFlight = flightRepository.findFlightById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));

        Flight updatedFlight = flightConverter.toEntity(flightDto);
        updatedFlight.setId(existingFlight.getId());
        flightRepository.save(updatedFlight);
    }

    @Override
    public List<FlightResponseDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(FlightConverter::toResponseDto)
                .toList();
    }

    @Override
    public Flight bookFlight(FlightDto flightDto) {
        Airport departureAirport = airportService.getAirport(flightDto.departureAirport());
        Airport arrivalAirport = airportService.getAirport(flightDto.arrivalAirport());

        if (!flightRepository.existsByDepartureAirportAndArrivalAirportAndDepartureTimeAndReturnTimeAndDepartureDateAndReturnDate(
                    departureAirport,
                    arrivalAirport,
                    flightDto.departureTime(),
                    flightDto.returnTime(),
                    flightDto.departureDate(),
                    flightDto.returnDate()
            )) {
                Flight flight = flightConverter.toEntity(flightDto);
                flightRepository.save(flight);
                return flight;

        }
        throw new FlightAlreadyExistsException();
    }

    @Override
    public FlightResponseDto getFlight(Long id) {
        Flight flight = flightRepository.findFlightById(id).orElseThrow(() -> new FlightNotFoundException(id));
        return FlightConverter.toResponseDto(flight);
    }

    @Override
    public List<FlightResponseDto> searchFlights(FlightDto flightDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> criteriaQuery = criteriaBuilder.createQuery(Flight.class);

        Root<Flight> flightRoot = criteriaQuery.from(Flight.class);

        List<Predicate> predicates = buildPredicates(criteriaBuilder, flightRoot, flightDto);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery)
                .getResultList()
                .stream()
                .map(FlightConverter::toResponseDto)
                .toList();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder criteriaBuilder, Root<Flight> flightRoot, FlightDto flightDto) {
        List<Predicate> predicates = new ArrayList<>();

        addEqualPredicate(criteriaBuilder, flightRoot.get("departureAirport").get("id"), flightDto.departureAirport())
                .ifPresent(predicates::add);

        addEqualPredicate(criteriaBuilder, flightRoot.get("arrivalAirport").get("id"), flightDto.arrivalAirport())
                .ifPresent(predicates::add);

        addEqualPredicate(criteriaBuilder, flightRoot.get("departureDate"), flightDto.departureDate())
                .ifPresent(predicates::add);

        addEqualPredicate(criteriaBuilder, flightRoot.get("returnDate"), flightDto.returnDate())
                .ifPresent(predicates::add);

        return predicates;
    }

    private Optional<Predicate> addEqualPredicate(CriteriaBuilder criteriaBuilder, Path<?> path, Object value) {
        return Optional.ofNullable(value)
                .map(val -> criteriaBuilder.equal(path, val));
    }
}
