package com.meetusar.assessment.dto;

import com.meetusar.assessment.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
    }

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
