package com.international.airports.controller;

import com.international.airports.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AirportController {

  @Autowired
  private AirportService airportService;

  //@PreAuthorize("hasRole('user') or hasRole('moderator') or hasRole('admin')")
  @RequestMapping("/api/airports")
  public List<String> getAirports(@RequestParam("search") final String letters) {
    return airportService.getAirportNamesByTypingLetters(letters);
  }
}
