package com.ecommerce.product_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stock;

}
