package com.international.airports.service;

import com.international.airports.custom.exceptions.UserAlreadyExistException;
import com.international.airports.domain.UserDto;
import com.international.airports.model.User;

public interface UserService {

  User registerNewUserAccount (UserDto userDto) throws UserAlreadyExistException;
}
