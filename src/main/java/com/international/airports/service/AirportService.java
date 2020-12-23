package com.international.airports.service;

import com.international.airports.model.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {

  List<String> populateRegionsAlphabetically();

  List<String> populateAirportsHeader();

  List<Airport> populateAirportSearchResults(String region);

  List<String> getAirportNamesByTypingLetters(String name);

  Optional<Airport> retrieveName(String name);

  String computeName(String name);

}
