package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product product) {
        // Set audit fields
        product.setCreatedAt(LocalDateTime.now());
        product.setCreatedBy("SYSTEM");
        return productRepository.save(product);
    }
}
