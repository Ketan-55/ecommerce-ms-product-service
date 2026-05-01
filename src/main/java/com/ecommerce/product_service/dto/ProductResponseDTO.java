package com.ecommerce.product_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProductResponseDTO {

    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private LocalDateTime createdAt;
    private String createdBy;

}
