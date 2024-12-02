package com.microservices.product_service.dao;

import com.microservices.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {
}
