package com.romitshrivastava.products.controller;


import com.romitshrivastava.products.dto.ProductDTO;
import com.romitshrivastava.products.dto.ProductResponseDTO;
import com.romitshrivastava.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@AllArgsConstructor
@Validated
@Slf4j
public class ProductController {

    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.info("Creating Product");
        log.info(productDTO.toString());
        ProductResponseDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long productId) {
        ProductResponseDTO productResponseDTO = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @Valid  @RequestBody ProductDTO productDTO) {
        ProductResponseDTO productResponseDTO = productService.updateProduct(productId , productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product Deleted");
    }

}
