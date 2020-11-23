package com.international.airports.service.impl;

import com.international.airports.model.Airport;
import com.international.airports.repository.AirportRepository;
import com.international.airports.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {

  @Autowired
  private AirportRepository airportRepository;

  @Override
  public List<String> getAirportListNames() {
    return airportRepository.findAll()
            .stream()
            .map(e -> e.getName())
            .collect(Collectors.toList());
  }

  @Override
  public List<String> populateRegionsAlphabetically() {
    final List<String> list = new ArrayList<>(new HashSet<>(airportRepository.findAll()
            .stream()
            .map(e -> getRegionFirstSubstring(e))
            .filter(e -> !e.equals(""))
            .collect(Collectors.toList())));
    Collections.sort(list);
    return list;
  }

  private String getRegionFirstSubstring(final Airport airport) {
    if (airport.getTz() != null) {
      int index = airport.getTz().indexOf("/");
      if (index != -1) {
        return airport.getTz().substring(0, index);
      } else {
        return "";
      }
    } else {
      return "Other";
    }
  }

  @Override
  public List<String> populateAirportsHeader() {
    final List<String> list = new ArrayList<>();
    list.add("Airport Name");
    list.add("Country");
    list.add("City");
    list.add("IATA");
    list.add("ICAO");
    list.add("Region");
    return list;
  }

  @Override
  public List<Airport> populateAirportSearchResults(final String region) {
    switch (region) {
      case "all":
        return airportRepository.findAll();
      case "Other":
        return airportRepository.findAll()
                .stream()
                .filter(e -> e.getTz() == null)
                .collect(Collectors.toList());
      default:
        return airportRepository.findAll()
                .stream()
                .filter(e -> notNull(e, region))
                .collect(Collectors.toList());
    }
  }

  private boolean notNull(final Airport airport, final String region) {
    if (airport.getTz() != null) {
      return airport.getTz().startsWith(region);
    }
    return false;
  }

}
