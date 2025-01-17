package com.microservices.product_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProductRequest {
    private List<ProductItem> productItems;
    private UUID sellerId;
    private String name;
    private String description;
    private BigDecimal price;
}
