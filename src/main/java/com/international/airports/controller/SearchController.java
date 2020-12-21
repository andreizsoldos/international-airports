package com.international.airports.controller;

import com.international.airports.model.Airline;
import com.international.airports.model.Airport;
import com.international.airports.service.AirlineService;
import com.international.airports.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

  @Autowired
  private AirportService airportService;

  @Autowired
  private AirlineService airlineService;

  @PreAuthorize("hasRole('user') or hasRole('moderator') or hasRole('admin')")
  @RequestMapping("/search-airport")
  public ModelAndView displayAirportSearchResults(@RequestParam("region") final String region) {
    final ModelAndView mav = new ModelAndView("airport-search");
    final List<Airport> listAirports = airportService.populateAirportSearchResults(region);
    mav.addObject("listAirports", listAirports);
    return mav;
  }

  @PreAuthorize("hasRole('user') or hasRole('moderator') or hasRole('admin')")
  @RequestMapping("/search-airline")
  public ModelAndView displayAirlineSearchResults() {
    final ModelAndView mav = new ModelAndView("airline-search");
    final List<Airline> listAirlines = airlineService.populateAirlineSearchResults();
    mav.addObject("listAirlines", listAirlines);
    return mav;
  }

}
