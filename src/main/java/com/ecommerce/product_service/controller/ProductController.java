package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.APIResponse;
import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public APIResponse<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request){

        ProductResponseDTO response = productService.createProduct(request);

        return new APIResponse<>("SUCCESS","Product created successfully",response);
    }
}
