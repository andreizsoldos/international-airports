package com.international.airports.service;

import com.international.airports.model.Airline;

import java.util.List;

public interface AirlineService {

  List<String> populateAirlinesHeader();

  List<Airline> populateAirlineSearchResults();

  List<String> getAirlineNamesByTypingLetters(String name);

}
