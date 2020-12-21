package com.international.airports.controller;

import com.international.airports.model.Airport;
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

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping("/infoPage")
  public ModelAndView displayInfo(@RequestParam("name") final String airName) {
    final ModelAndView mav = new ModelAndView("info");
    final Optional<Airport> optAirport = airportService.retrieveName(airName);
    final Airport currentAirport = optAirport.stream()
            .findAny()
            .orElse(new Airport());
    mav.addObject("airport_info", currentAirport);
    return mav;
  }

}
