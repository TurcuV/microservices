package com.microservices.user_service.dto;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
@Service
public class UserRequest {
  private String userLogin;

  private String firstName;

  private String lastName;

  private String email;

  private String password;

  private String phoneNumber;

}
