package com.international.airports.service;

import com.international.airports.model.Flight;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface FlightService {

  List<String> populateFlightsHeader();

  void addNewFlightToDatabase(Flight flight);

  List<Flight> retrieveAllFlights();

  Flight transformFlight(String newFlightNo,
                         String newDepartureDateTime,
                         String newDepartureAirport,
                         String newArrivalDateTime,
                         String newArrivalAirport,
                         String newAirline);

  DateTimeFormatter formatDateTime(String pattern);
}
