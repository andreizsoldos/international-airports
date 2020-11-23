package com.international.airports.service;

import com.international.airports.model.Airport;

import java.util.List;

public interface AirportService {

  List<String> populateRegionsAlphabetically();

  List<String> populateAirportsHeader();

  List<Airport> populateAirportSearchResults(String region);

  List<String> getAirportListNames();

}
