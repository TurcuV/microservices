package com.microservices.product_service.controller;

import com.microservices.product_service.dto.ProductRequest;
import com.microservices.product_service.model.Product;
import com.microservices.product_service.pagegenerator.HtmlComponentType;
import com.microservices.product_service.pagegenerator.annotations.AutoGenerateComponent;
import com.microservices.product_service.pagegenerator.annotations.AutoGeneratePage;
import com.microservices.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@AutoGeneratePage(name = "Product Service")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @AutoGenerateComponent(
        classType = ProductRequest.class,
        type = HtmlComponentType.FORM
    )
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.saveProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
//    @AutoGenerateGrid
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
