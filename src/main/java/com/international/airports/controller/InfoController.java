package com.international.airports.controller;

import com.international.airports.model.Airport;
import com.international.airports.repository.AirportRepository;
import com.international.airports.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class InfoController {

  @Autowired
  private AirportService airportService;

  @Autowired
  private AirportRepository airportRepository;

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping("/infoPage")
  public ModelAndView displayInfo(@RequestParam("name") final String airName) {
    Optional<Airport> optAirport;
    final ModelAndView mav = new ModelAndView("info");
    if (airName.contains("--[")) {
      optAirport = airportService.retrieveName(airName);
    } else {
      optAirport = airportRepository.findByName(airName);
    }
    final Airport currentAirport = optAirport.stream()
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("This airport name doesn't exists in database!"));
    mav.addObject("airport_info", currentAirport);
    return mav;
  }

}
