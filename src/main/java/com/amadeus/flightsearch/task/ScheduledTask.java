package com.amadeus.flightsearch.task;

import com.amadeus.flightsearch.dto.FlightDto;
import com.amadeus.flightsearch.exception.custom_exceptions.JsonConvertingException;
import com.amadeus.flightsearch.service.AirportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Component
public class ScheduledTask {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AirportService airportService;
    private static final Logger logger = Logger.getLogger(ScheduledTask.class.getName());
    private static final Random random = new Random();


    @Scheduled(cron = "0 32 13 * * *") // Run every day at 6am
    public void generateAndSendMockApiRequest() {
        FlightDto mockFlight = createMockFlight();

        String requestJson = convertObjectToJson(mockFlight);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        String apiUrl = "http://localhost:8080/mock-api/v1/flights";

        restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        logger.info("Mock API request sent on " + LocalDate.now() + " at " + LocalTime.now());
    }

    private FlightDto createMockFlight() {
        long airportCount = airportService.getAirportCount();
        long departureAirport = random.nextLong(airportCount) + 1;
        long arrivalAirport;
        do {
            arrivalAirport = random.nextLong(airportCount) + 1;
        } while (arrivalAirport == departureAirport);

        LocalDate departureDate = generateRandomDate();
        LocalDate returnDate;
        do {
            returnDate = generateRandomDate();
        } while(!returnDate.isAfter(departureDate));

        return FlightDto.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(departureDate)
                .returnDate(returnDate)
                .departureTime(generateRandomTime())
                .returnTime(generateRandomTime())
                .build();
    }

    private LocalDate generateRandomDate() {
        int month = random.nextInt(12) + 1;      // 1 to 12
        int day = random.nextInt(28) + 1;

        return LocalDate.of(2024, month, day);
    }

    private LocalTime generateRandomTime() {
        int hour = random.nextInt(24);          // 0 to 23
        int minute = random.nextInt(4) * 15;    // 0, 15, 30, 45

        return LocalTime.of(hour, minute);
    }

    private String convertObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonConvertingException();
        }
    }
}
