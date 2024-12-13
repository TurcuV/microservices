package com.microservices.seller_service.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerRequest {
  private UUID userId;
  private String storeName;
  private String storeDescription;
  private LocalDate dateJoined;
}
