package com.international.airports.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomErrorController{

  @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
  @ExceptionHandler(IllegalArgumentException.class)
  public ModelAndView illegalArgumentHandler(final HttpServletRequest req, final HttpServletResponse res, final Exception e) throws Exception {
    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
      throw e;
    }
    final ModelAndView mav = new ModelAndView("error");
    mav.addObject("status", res.getStatus());
    mav.addObject("message", e.getLocalizedMessage());
    mav.addObject("path", req.getRequestURI());
    return mav;
  }

  @ResponseStatus(HttpStatus.FORBIDDEN) // 403
  @ExceptionHandler(AccessDeniedException.class)
  public ModelAndView accessDeniedHandler(final HttpServletRequest req, final HttpServletResponse res, final Exception e) throws Exception {
    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
      throw e;
    }
    final ModelAndView mav = new ModelAndView("error");
    mav.addObject("status", res.getStatus());
    mav.addObject("message", "You do not have permission to access this content!");
    mav.addObject("path", req.getRequestURI());
    return mav;
  }
}
