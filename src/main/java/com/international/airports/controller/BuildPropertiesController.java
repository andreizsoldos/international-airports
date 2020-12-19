package com.international.airports.controller;

import com.international.airports.service.AirlineService;
import com.international.airports.service.AirportService;
import com.international.airports.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class BuildPropertiesController {

  @Autowired
  AirportService airportService;

  @Autowired
  AirlineService airlineService;

  @Autowired
  FlightService flightService;

  @ModelAttribute("airlineListNames")
  public List<String> getAirlineListNames() {
    return airlineService.getAirlineListNames();
  }

  @ModelAttribute("regions")
  public List<String> getRegionList() {
    return airportService.populateRegionsAlphabetically();
  }

  @ModelAttribute("airportRegionsHeader")
  public List<String> getAirportSearchHeaderList() {
    return airportService.populateAirportsHeader();
  }

  @ModelAttribute("airlineRegionsHeader")
  public List<String> getAirLineSearchHeaderList() {
    return airlineService.populateAirlinesHeader();
  }

  @ModelAttribute("flightsHeader")
  public List<String> getFlightsHeaderList() {
    return flightService.populateFlightsHeader();
  }

}
