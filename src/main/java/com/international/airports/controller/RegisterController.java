package com.international.airports.controller;

import com.international.airports.domain.UserDto;
import com.international.airports.domain.validator.UserDtoInputValidator;
import com.international.airports.model.User;
import com.international.airports.repository.UserRepository;
import com.international.airports.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @InitBinder
  protected void initBinder(final WebDataBinder binder) {
    binder.setValidator(new UserDtoInputValidator(userRepository));
  }

  @RequestMapping("/register")
  public ModelAndView displayRegisterPage() {
    final ModelAndView mav = new ModelAndView("register");
    mav.addObject("newUser", new UserDto());
    return mav;
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView registerUserAccount(@Valid @ModelAttribute("newUser") final UserDto userDto, final BindingResult result) {
    final ModelAndView mav = new ModelAndView("register");

    if (result.hasErrors()) {
      return mav;
    }

    User registered = userService.registerNewUserAccount(userDto);

    return new ModelAndView("success-register", "user", userDto);
  }
}
