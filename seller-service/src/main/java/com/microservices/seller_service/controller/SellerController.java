package com.microservices.seller_service.controller;

import com.microservices.seller_service.dto.SellerRequest;
import com.microservices.seller_service.model.Seller;
import com.microservices.seller_service.service.SellerService;

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
@RequestMapping("/api/seller")
@AllArgsConstructor
public class SellerController {

  private final SellerService sellerService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createSeller(@RequestBody SellerRequest sellerRequest) {
    sellerService.createSeller(sellerRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Seller> getAllSellers() {
    return sellerService.getAllSellers();
  }
}
