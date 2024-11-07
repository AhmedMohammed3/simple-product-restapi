package com.meetusar.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
