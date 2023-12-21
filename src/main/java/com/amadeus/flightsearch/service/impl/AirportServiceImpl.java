package com.amadeus.flightsearch.service.impl;

import com.amadeus.flightsearch.dto.AirportDto;
import com.amadeus.flightsearch.entity.Airport;
import com.amadeus.flightsearch.exception.AirportAlreadyExistsException;
import com.amadeus.flightsearch.exception.AirportNotFoundException;
import com.amadeus.flightsearch.repository.AirportRepository;
import com.amadeus.flightsearch.service.AirportService;
import com.amadeus.flightsearch.util.converter.AirportConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportConverter airportConverter;
    @Override
    public void deleteAirport(Long id) {
        Optional<Airport> airport = airportRepository.findAirportById(id);
        airport.ifPresentOrElse(airportRepository::delete,
                () -> {
                    throw new AirportNotFoundException(id);
                }
        );
    }

    @Override
    public void updateAirport(Long id, AirportDto airportDto) {
        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));

        Airport updatedAirport = airportConverter.toEntity(airportDto);
        updatedAirport.setId(existingAirport.getId());

        airportRepository.save(updatedAirport);
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport saveAirport(AirportDto airportDto) {
        if (!airportRepository.existsAirportByCity(airportDto.city())) {
            Airport airport = airportConverter.toEntity(airportDto);
            airportRepository.save(airport);
            return airport;
        }
        throw new AirportAlreadyExistsException("Airport already exists in " + airportDto.city());
    }

    @Override
    public AirportDto getAirportDto(Long id) {
        Airport airport = airportRepository.findAirportById(id).orElseThrow(() -> new AirportNotFoundException(id));
        return AirportConverter.toDto(airport);
    }

    @Override
    public Airport getAirport(Long id) {
        return airportRepository.findAirportById(id).orElseThrow(() -> new AirportNotFoundException(id));
    }
}
