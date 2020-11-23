package com.international.airports.service.impl;

import com.international.airports.custom.exceptions.UserAlreadyExistException;
import com.international.airports.domain.UserDto;
import com.international.airports.model.User;
import com.international.airports.repository.UserRepository;
import com.international.airports.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public User registerNewUserAccount(final UserDto userDto) throws UserAlreadyExistException {
    if (emailExists(userDto.getEmail())) {
      throw new UserAlreadyExistException ("** There is already an account with that email address: " + userDto.getEmail() + "**");
    }

    User user = new User();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setEmail(userDto.getEmail());
    user.setRole("user");
    return userRepository.save(user);
  }

  private boolean emailExists(final String email) {
    final Optional<User> optionalUser = userRepository.findByEmail(email);
    return optionalUser.isPresent();
  }
}
