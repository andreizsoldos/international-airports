package com.international.airports.service.impl;

import com.international.airports.model.Airline;
import com.international.airports.repository.AirlineRepository;
import com.international.airports.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirlineServiceImpl implements AirlineService {

  @Autowired
  private AirlineRepository airlineRepository;

  @Override
  public List<String> populateAirlinesHeader() {
    final List<String> list = new ArrayList<>();
    list.add("Airline Name");
    list.add("Country");
    list.add("IATA");
    list.add("ICAO");
    list.add("Active");
    return list;
  }

  @Override
  public List<Airline> populateAirlineSearchResults() {
    return airlineRepository.findAll();
  }
}
