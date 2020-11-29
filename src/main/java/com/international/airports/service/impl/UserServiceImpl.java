package com.international.airports.service.impl;

import com.international.airports.domain.UserDto;
import com.international.airports.model.User;
import com.international.airports.repository.UserRepository;
import com.international.airports.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public User registerNewUserAccount(final UserDto userDto) {
    User user = new User();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setEmail(userDto.getEmail());
    user.setRole("user");
    return userRepository.save(user);
  }
}
