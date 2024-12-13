package com.microservices.user_service.controller;

import com.microservices.user_service.dto.UserRequest;
import com.microservices.user_service.model.User;
import com.microservices.user_service.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private void createUser(@RequestBody UserRequest userRequest) {
    userService.createUser(userRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  private List<User> getAllUsers()  {
    return userService.getAllUsers();
  }
}
