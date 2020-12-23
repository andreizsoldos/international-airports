package com.international.airports.service.impl;

import com.international.airports.domain.FlightDto;
import com.international.airports.model.Airline;
import com.international.airports.model.Airport;
import com.international.airports.model.Flight;
import com.international.airports.repository.FlightRepository;
import com.international.airports.service.AirlineService;
import com.international.airports.service.AirportService;
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
  private AirportService airportService;

  @Autowired
  private AirlineService airlineService;

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
  public FlightDto findCurrentFlight(final long id) {
    final Flight currentFlight = Optional.ofNullable(flightRepository.findById(id))
            .orElseThrow(() -> new IllegalArgumentException("Cannot find Flight ID " + id + ". No record found in database!"));
    final FlightDto thisFlight = new FlightDto();
    thisFlight.setId(currentFlight.getId());
    thisFlight.setFlightNo(currentFlight.getFlightNo());
    thisFlight.setDeparture(currentFlight.getDeparture());
    thisFlight.setDepartureAirportName(airportService.computeName(currentFlight.getDepartureAirport().getName()));
    thisFlight.setArrival(currentFlight.getArrival());
    thisFlight.setArrivalAirportName(airportService.computeName(currentFlight.getArrivalAirport().getName()));
    thisFlight.setAirlineName(airlineService.computeName(currentFlight.getAirline().getName()));

    return thisFlight;
  }

  @Override
  public Integer deleteSelectedFlight(final long id) {
    return Optional.ofNullable(flightRepository.customDeleteById(id))
            .orElseThrow(() -> new IllegalArgumentException("Cannot delete Flight ID " + id + ". No record found in database!"));
  }

  @Override
  public Integer editFlight(final long currentId,
                           final String currentFlightNo,
                           final String currentDepartureDateTime,
                           final String currentDepartureAirport,
                           final String currentArrivalDateTime,
                           final String currentArrivalAirport,
                           final String currentAirline) {

    return Optional.ofNullable(flightRepository.customUpdateById(currentId,
                            currentFlightNo,
                            LocalDateTime.parse(currentDepartureDateTime),
                            getAirport(currentDepartureAirport),
                            LocalDateTime.parse(currentArrivalDateTime),
                            getAirport(currentArrivalAirport),
                            getAirline(currentAirline)))
            .orElseThrow(() -> new IllegalArgumentException("Cannot edit Flight ID " + currentId + ". No record found in database!"));
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
    Optional<Airport> optAirport = airportService.retrieveName(name);
    return optAirport.stream()
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("This airport name doesn't exists in database!"));
  }

  private Airline getAirline(final String name) {
    Optional<Airline> optAirline = airlineService.retrieveName(name);
    return optAirline.stream()
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("This airline name doesn't exists in database!"));
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
