package com.microservices.seller_service.service;

import com.microservices.seller_service.dto.SellerRequest;
import com.microservices.seller_service.model.Seller;
import com.microservices.seller_service.repository.SellerRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SellerService {

  private final SellerRepository sellerRepository;

  public void createSeller(SellerRequest sellerRequest) {
    Seller seller = Seller.builder()
//        .userId(sellerRequest.getUserId())
        .userId(UUID.randomUUID())
        .storeName(sellerRequest.getStoreName())
        .storeDescription(sellerRequest.getStoreDescription())
        .build();
    sellerRepository.save(seller);
    log.info("Seller successfully added!!!");
  }

  public List<Seller> getAllSellers() {
    return sellerRepository.findAll();
  }
}
