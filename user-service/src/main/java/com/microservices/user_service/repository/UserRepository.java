package com.microservices.user_service.repository;

import com.microservices.user_service.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
