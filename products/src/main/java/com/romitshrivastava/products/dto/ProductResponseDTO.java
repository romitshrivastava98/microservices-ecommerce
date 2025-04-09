package com.romitshrivastava.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductResponseDTO {

    private Long productId;

    private String productName;

    private String productDescription;

    private String productCategory;

    private double productPrice;

    private int availableQuantity;
}
