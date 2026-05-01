package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

    private static  final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        // Map ProductRequestDTO to Product entity
        log.info("Creating product with name: {}", request.getName());
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        // Set audit fields
        product.setCreatedAt(LocalDateTime.now());
        product.setCreatedBy("SYSTEM");

        log.info("Saving product to MongoDB");
        
        Product savedProduct = productRepository.save(product);

        log.info("Product saved with id: {}", savedProduct.getId());

        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setStock(savedProduct.getStock());
        response.setCreatedAt(savedProduct.getCreatedAt());
        response.setCreatedBy(savedProduct.getCreatedBy());
        return response;
    }
}
