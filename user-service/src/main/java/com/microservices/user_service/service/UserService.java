package com.microservices.user_service.service;

import com.microservices.user_service.dto.UserRequest;
import com.microservices.user_service.model.User;
import com.microservices.user_service.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  public void createUser(UserRequest userRequest) {
    User user = User.builder()
        .userLogin(userRequest.getUserLogin())
        .email(userRequest.getEmail())
        .firstName(userRequest.getFirstName())
        .lastName(userRequest.getLastName())
        .phoneNumber(userRequest.getPhoneNumber())
        .password(userRequest.getPassword())
        .build();

    userRepository.save(user);

    log.info("Users successfully created!!!");
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
