package com.international.airports.controller;

import com.international.airports.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirportController {

  @Autowired
  private AirportService airportService;

  @PreAuthorize("hasRole('user') or hasRole('moderator') or hasRole('admin')")
  @CrossOrigin("http://localhost:1234/app")
  @RequestMapping("/api/airports")
  public ResponseEntity<List<String>> getAirports(@RequestBody @RequestParam("search") final String letters) {
    return ResponseEntity.ok(airportService.getAirportNamesByTypingLetters(letters));
  }
}
