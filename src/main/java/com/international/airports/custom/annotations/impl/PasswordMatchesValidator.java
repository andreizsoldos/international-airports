package com.international.airports.custom.annotations.impl;

import com.international.airports.custom.annotations.PasswordMatches;
import com.international.airports.domain.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(final PasswordMatches constraintAnnotation) {
  }

  @Override
  public boolean isValid(final Object obj, final ConstraintValidatorContext context){
    UserDto user = (UserDto) obj;
    return user.getPassword().equals(user.getMatchingPassword());
  }
}
