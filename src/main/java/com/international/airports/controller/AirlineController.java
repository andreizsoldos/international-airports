package com.international.airports.controller;

import com.international.airports.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AirlineController {

  @Autowired
  private AirlineService airlineService;

  //@PreAuthorize("hasRole('user') or hasRole('moderator') or hasRole('admin')")
  @RequestMapping("/api/airlines")
  public List<String> getAirlines(@RequestParam("search") final String letters) {
    return airlineService.getAirlineNamesByTypingLetters(letters);
  }
}
