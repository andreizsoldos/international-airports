package com.international.airports.service;

import com.international.airports.model.Flight;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface FlightService {

  List<String> populateFlightsHeader();

  List<Flight> retrieveAllFlights();

  Flight addNewFlightToDatabase(String newFlightNo,
                         String newDepartureDateTime,
                         String newDepartureAirport,
                         String newArrivalDateTime,
                         String newArrivalAirport,
                         String newAirline);

  DateTimeFormatter formatDateTime(String pattern);

  Integer deleteSelectedFlight(Long id);

}
