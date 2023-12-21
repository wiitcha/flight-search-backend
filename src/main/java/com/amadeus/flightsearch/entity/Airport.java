package com.amadeus.flightsearch.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureFlightsList;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlightsList;
}
