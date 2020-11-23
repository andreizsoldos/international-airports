package com.international.airports.domain;

import com.international.airports.custom.annotations.PasswordMatches;
import com.international.airports.custom.annotations.ValidEmail;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

@PasswordMatches
public class UserDto {

  @NotNull
  @NotEmpty(message = "* First name is required")
  private String firstName;

  @NotNull
  @NotEmpty(message = "* Last name is required")
  private String lastName;

  @NotNull
  @NotEmpty(message = "* Password is required")
  private String password;

  @NotNull
  @NotEmpty(message = "* Matching password is required")
  private String matchingPassword;

  @ValidEmail
  @NotNull
  @NotEmpty(message = "* Email is required")
  private String email;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}