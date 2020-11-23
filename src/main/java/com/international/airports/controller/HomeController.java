package com.international.airports.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @RequestMapping({"/", "/index", "/home"})
  public ModelAndView displayHomePage() {
    final ModelAndView mav = new ModelAndView("home-page");
    return mav;
  }

  @RequestMapping("/about")
  public ModelAndView displayAboutPage() {
    final ModelAndView mav = new ModelAndView("about");
    return mav;
  }

}
