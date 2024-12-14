package com.microservices.seller_service.repository;

import com.microservices.seller_service.model.Seller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
}
