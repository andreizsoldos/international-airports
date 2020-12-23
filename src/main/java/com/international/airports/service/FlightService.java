package com.international.airports.service;

import com.international.airports.domain.FlightDto;
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

  Integer editFlight(long currentId,
              String currentFlightNo,
              String currentDepartureDateTime,
              String currentDepartureAirport,
              String currentArrivalDateTime,
              String currentArrivalAirport,
              String currentAirline);

  DateTimeFormatter formatDateTime(String pattern);

  Integer deleteSelectedFlight(long id);

  FlightDto findCurrentFlight(long id);
}
