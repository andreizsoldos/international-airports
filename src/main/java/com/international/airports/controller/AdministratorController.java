package com.international.airports.controller;

import com.international.airports.model.Flight;
import com.international.airports.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AdministratorController {

  @Autowired
  private FlightService flightService;

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping("/admin")
  public ModelAndView displayAdminPage() {
    final ModelAndView mav = new ModelAndView("administration");
    final List<Flight> list = flightService.retrieveAllFlights();
    final DateTimeFormatter dateTimeFormatter = flightService.formatDateTime("dd-MM-yyyy HH:mm");
    mav.addObject("formatter", dateTimeFormatter);
    mav.addObject("flightList", list);
    return mav;
  }
}
