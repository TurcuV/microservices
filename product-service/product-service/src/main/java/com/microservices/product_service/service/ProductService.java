package com.microservices.product_service.service;

import com.microservices.product_service.dao.ProductDAO;
import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductDAO productDAO;



    public void saveProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productDAO.save(product);
        log.info("Saved product: {}", product.getId());
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
}
