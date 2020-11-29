package com.international.airports.domain.validator;

import com.international.airports.domain.UserDto;
import com.international.airports.model.User;
import com.international.airports.repository.UserRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

public class UserDtoInputValidator implements Validator {

  private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

  private UserRepository userRepository;

  public UserDtoInputValidator(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean supports(final Class<?> paramClass) {
    return UserDto.class.equals(paramClass);
  }

  public void validate(final Object obj, final Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matchingPassword", "matchingPassword.empty");

    UserDto user = (UserDto) obj;
    if (!user.getPassword().equals(user.getMatchingPassword())) {
      errors.rejectValue("matchingPassword", "matchingPassword.different");
    }
    if (!user.getEmail().matches(EMAIL_PATTERN)) {
      errors.rejectValue("email", "email.invalid");
    }
    if (emailExists(user.getEmail())) {
      errors.rejectValue("email", "email.exists");
    }
  }

  private boolean emailExists(final String email) {
    final Optional<User> optionalUser = userRepository.findByEmail(email);
    return optionalUser.isPresent();
  }
}
