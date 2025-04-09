package com.romitshrivastava.products.service;

import com.romitshrivastava.products.dto.ProductResponseDTO;
import com.romitshrivastava.products.dto.ProductDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {

    // 1. Create
        ProductResponseDTO createProduct(@Valid ProductDTO product);
    // 2. Get All
        List<ProductResponseDTO> getAllProducts();
    // 3. Get One
        ProductResponseDTO getProductById(Long productId);
    // 4. Update
        ProductResponseDTO updateProduct(Long productId, @Valid ProductDTO product);
    // 5. Delete
        void deleteProduct(Long productId);
}
