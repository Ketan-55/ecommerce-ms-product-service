package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.exception.ProductNotFoundException;
import com.ecommerce.product_service.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public ProductResponseDTO getProductById(String id){
        log.info("Fetching product with id: {}", id);

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            log.error("Product not found with id: {}", id);
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

        Product product = optionalProduct.get();

        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCreatedAt(product.getCreatedAt());
        response.setCreatedBy(product.getCreatedBy());

        return response;

    }

    public ProductResponseDTO reduceStock(String id,Integer quantity){

        log.info("Reducing stock for product with id {} by quantity {}",id,quantity);

        Optional<Product> product = productRepository.findById(id);

            if(!product.isPresent()){
                log.error("Product not found with id: {}",id);
                throw  new ProductNotFoundException("Product not found with id: " + id);
            }

            if(product.get().getStock()<quantity){
                log.error("Insufficient stock for product with id {}: available {},requested {}",id,product.get().getStock(),quantity);
                throw  new IllegalArgumentException("Insufficient stock for product with id: " + id);
            }

            product.get().setStock((product.get().getStock())-quantity);

            Product updatedProduct = productRepository.save(product.get());

            ProductResponseDTO response = new ProductResponseDTO();
            response.setId(updatedProduct.getId());
            response.setName(updatedProduct.getName());
            response.setStock(updatedProduct.getStock());

            return response;
    }
}
