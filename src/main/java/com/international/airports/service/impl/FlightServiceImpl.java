package com.international.airports.service.impl;

import com.international.airports.model.Airline;
import com.international.airports.model.Airport;
import com.international.airports.model.Flight;
import com.international.airports.repository.AirlineRepository;
import com.international.airports.repository.AirportRepository;
import com.international.airports.repository.FlightRepository;
import com.international.airports.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

  @Autowired
  private FlightRepository flightRepository;

  @Autowired
  private AirportRepository airportRepository;

  @Autowired
  private AirlineRepository airlineRepository;

  @Override
  public List<String> populateFlightsHeader() {
    final List<String> list = new ArrayList<>();
    list.add("#");
    list.add("Flight Number");
    list.add("Airline Company");
    list.add("Departure Date/Time");
    list.add("Departure Airport");
    list.add("Arrival Date/Time");
    list.add("Arrival Airport");
    return list;
  }

  @Override
  public Flight addNewFlightToDatabase(final String newFlightNo,
                                final String newDepartureDateTime,
                                final String newDepartureAirport,
                                final String newArrivalDateTime,
                                final String newArrivalAirport,
                                final String newAirline) {
    final Flight newFlight = new Flight();
    newFlight.setFlightNo(newFlightNo);
    newFlight.setDeparture(LocalDateTime.parse(newDepartureDateTime));
    newFlight.setDepartureAirport(getAirport(newDepartureAirport));
    newFlight.setArrival(LocalDateTime.parse(newArrivalDateTime));
    newFlight.setArrivalAirport(getAirport(newArrivalAirport));
    newFlight.setAirline(getAirline(newAirline));
    flightRepository.save(newFlight);
    return newFlight;
  }

  private Airport getAirport(final String name) {
    final Optional<Airport> optAirport = airportRepository.findByName(name);
    return optAirport.stream()
            .findAny()
            .orElse(new Airport());
  }

  private Airline getAirline(final String name) {
    final Optional<Airline> optAirline = airlineRepository.findByName(name);
    return optAirline.stream()
            .findAny()
            .orElse(new Airline());
  }

  @Override
  public List<Flight> retrieveAllFlights() {
    return flightRepository.findAll();
  }

  @Override
  public DateTimeFormatter formatDateTime(final String pattern) {
    return DateTimeFormatter.ofPattern(pattern);
  }
}
