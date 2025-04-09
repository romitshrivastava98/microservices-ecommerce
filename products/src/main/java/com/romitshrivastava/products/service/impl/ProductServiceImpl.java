package com.romitshrivastava.products.service.impl;


import com.romitshrivastava.products.dto.ProductResponseDTO;
import com.romitshrivastava.products.dto.ProductDTO;
import com.romitshrivastava.products.entity.Product;
import com.romitshrivastava.products.exceptions.ProductAlreadyExistsException;
import com.romitshrivastava.products.exceptions.ResourceNotFoundException;
import com.romitshrivastava.products.mapper.ProductMapper;
import com.romitshrivastava.products.repository.ProductRepository;
import com.romitshrivastava.products.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductDTO product) {
        log.info("Creating Product Inside Service Layer");
        Optional<Product> productOptional = productRepository.findByProductName(product.getProductName());
        if(productOptional.isPresent()){
            log.info("Product with name " + product.getProductName() + " already exists");
            throw new ProductAlreadyExistsException("Product with name " + product.getProductName() + " already exists");
        }
        log.info("Creating a New Product");
        Product savedProduct = productRepository.save(ProductMapper.mapToProduct(product, new Product()));
        return ProductMapper.mapToProductResponseDTO(savedProduct, new ProductResponseDTO());
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();
        return products.stream().
                map(product ->
                        ProductMapper.mapToProductResponseDTO(product, new ProductResponseDTO())).toList();

    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            log.info("Product with id " + productId + " not found");
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        }
        log.info("Product with id " + productId + " found");
        return ProductMapper.mapToProductResponseDTO(productOptional.get(), new ProductResponseDTO());
    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductDTO product) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            log.info("Product with id " + productId + " not found");
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        }
        log.info("Product with id " + productId + " found");
        log.info("Updating Product with id " + productId);
        Product updatedProduct = productOptional.get();
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setProductPrice(product.getProductPrice());
        updatedProduct.setProductCategory(product.getProductCategory());
        updatedProduct.setProductDescription(product.getProductDescription());
        updatedProduct.setAvailableQuantity(product.getAvailableQuantity());
        productRepository.save(updatedProduct);
        log.info("Product with id " + productId + " updated");
        return ProductMapper.mapToProductResponseDTO(updatedProduct, new ProductResponseDTO());

    }

    @Override
    public void deleteProduct(Long productId) {

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            log.info("Product with id " + productId + " not found");
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        }
        log.info("Product with id " + productId + " found");
        log.info("Deleting Product with id " + productId);
        productRepository.deleteById(productId);
        log.info("Product with id " + productId + " deleted");
    }
}
