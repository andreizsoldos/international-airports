package com.international.airports.controller;

import com.international.airports.custom.exceptions.UserAlreadyExistException;
import com.international.airports.domain.UserDto;
import com.international.airports.model.User;
import com.international.airports.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

  @Autowired
  private UserService userService;

  @RequestMapping("/register")
  public ModelAndView displayRegisterPage() {
    final ModelAndView mav = new ModelAndView("register");
    mav.addObject("newUser", new UserDto());
    return mav;
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView registerUserAccount(@ModelAttribute("newUser") @Valid final UserDto userDto, BindingResult result) {
    final ModelAndView mav = new ModelAndView("register");

    if (result.hasErrors()) {
      return mav;
    }

    try {
      User registered = userService.registerNewUserAccount(userDto);
    } catch (UserAlreadyExistException e) {
      mav.addObject("exists", e.getMessage());
      return mav;
    }

    return new ModelAndView("success-register", "user", userDto);
  }
}
