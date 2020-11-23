package com.international.airports.controller;

import com.international.airports.model.Flight;
import com.international.airports.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FlightController {

  @Autowired
  private FlightService flightService;

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping(value = "/add-new-flight", method = RequestMethod.GET)
  public ModelAndView displayNewFlightFormPage() {
    final ModelAndView mav = new ModelAndView("new-flight");
    mav.addObject("newFlight", new Flight());
    return mav;
  }

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping(value = "/add-new-flight", method = RequestMethod.POST)
  public ModelAndView displayPostFlightFormPage(@ModelAttribute("flightNo") final String newFlightNo,
                                                @ModelAttribute("departure") final String newDepartureDateTime,
                                                @ModelAttribute("departureAirport") final String newDepartureAirport,
                                                @ModelAttribute("arrival") final String newArrivalDateTime,
                                                @ModelAttribute("arrivalAirport") final String newArrivalAirport,
                                                @ModelAttribute("airline") final String newAirline) {
    final ModelAndView mav = new ModelAndView("redirect:/admin");
    final Flight newFlight = flightService.transformFlight(newFlightNo,
                                                           newDepartureDateTime,
                                                           newDepartureAirport,
                                                           newArrivalDateTime,
                                                           newArrivalAirport,
                                                           newAirline);
    flightService.addNewFlightToDatabase(newFlight);
    return mav;
  }
}
