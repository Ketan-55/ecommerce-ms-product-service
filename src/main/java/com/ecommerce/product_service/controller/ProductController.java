package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.APIResponse;
import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public APIResponse<ProductResponseDTO>getProductById(@PathVariable String id){
        ProductResponseDTO response = productService.getProductById(id);
        return new APIResponse<>("SUCCESS","Product fetched successfully",response);
    }

    @PutMapping("/{id}/reduce-stock")
    public APIResponse<ProductResponseDTO>reduceStock(@PathVariable String id, @RequestParam Integer quantity){
        ProductResponseDTO response = productService.reduceStock(id, quantity);
        return new APIResponse<>("SUCCESS","Stock reduced successfully",response);
    }
}
