package com.international.airports.service;

import com.international.airports.model.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {

  List<String> populateAirlinesHeader();

  List<Airline> populateAirlineSearchResults();

  List<String> getAirlineNamesByTypingLetters(String name);

  Optional<Airline> retrieveName(String name);

  String computeName(String name);

}
