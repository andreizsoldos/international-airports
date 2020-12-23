package com.international.airports.controller;

import com.international.airports.model.Flight;
import com.international.airports.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FlightController {

  @Autowired
  private FlightService flightService;

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping(value = "/admin/add-new-flight", method = RequestMethod.GET)
  public ModelAndView displayNewFlightFormPage() {
    final ModelAndView mav = new ModelAndView("new-flight");
    mav.addObject("newFlight", new Flight());
    return mav;
  }

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping(value = "/admin/add-new-flight", method = RequestMethod.POST)
  public ModelAndView displayPostFlightFormPage(@ModelAttribute("flightNo") final String newFlightNo,
                                                @ModelAttribute("departure") final String newDepartureDateTime,
                                                @ModelAttribute("departureAirport") final String newDepartureAirport,
                                                @ModelAttribute("arrival") final String newArrivalDateTime,
                                                @ModelAttribute("arrivalAirport") final String newArrivalAirport,
                                                @ModelAttribute("airline") final String newAirline) {
    final ModelAndView mav = new ModelAndView("redirect:/admin");
    final Flight newFlight = flightService.addNewFlightToDatabase(newFlightNo,
                                                           newDepartureDateTime,
                                                           newDepartureAirport,
                                                           newArrivalDateTime,
                                                           newArrivalAirport,
                                                           newAirline);
    return mav;
  }

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping("/admin/delete-flight")
  public ModelAndView deleteFlight(@RequestParam("id") final long id) {
    final ModelAndView mav = new ModelAndView("redirect:/admin");
    final Integer delFlightId = Optional.ofNullable(flightService.deleteSelectedFlight(id)).orElse(-1);
    return mav;
  }

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping(value = "/admin/edit-flight", method = RequestMethod.GET)
  public ModelAndView viewEditFlightPage(@RequestParam("id") final long id) {
    final ModelAndView mav = new ModelAndView("edit-flight");
    mav.addObject("editFlight", flightService.findCurrentFlight(id));
    return mav;
  }

  @PreAuthorize("hasRole('moderator') or hasRole('admin')")
  @RequestMapping(value = "/admin/edit-flight", method = RequestMethod.POST)
  public ModelAndView editFlight(@RequestParam("id") final long currentId,
                                 @ModelAttribute("flightNo") final String currentFlightNo,
                                 @ModelAttribute("departure") final String currentDepartureDateTime,
                                 @ModelAttribute("departureAirportName") final String currentDepartureAirport,
                                 @ModelAttribute("arrival") final String currentArrivalDateTime,
                                 @ModelAttribute("arrivalAirportName") final String currentArrivalAirport,
                                 @ModelAttribute("airlineName") final String currentAirline) {
    final ModelAndView mav = new ModelAndView("redirect:/admin");
    final Integer editFlightId = Optional.ofNullable(flightService.editFlight(currentId,
                                 currentFlightNo,
                                 currentDepartureDateTime,
                                 currentDepartureAirport,
                                 currentArrivalDateTime,
                                 currentArrivalAirport,
                                 currentAirline)).orElse(-1);
    return mav;
  }
}
