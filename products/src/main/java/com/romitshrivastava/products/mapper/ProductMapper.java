package com.romitshrivastava.products.mapper;

import com.romitshrivastava.products.dto.ProductDTO;
import com.romitshrivastava.products.dto.ProductResponseDTO;
import com.romitshrivastava.products.entity.Product;

public class ProductMapper {

    public static Product mapToProduct(ProductDTO productDTO , Product product) {
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductCategory(productDTO.getProductCategory());
        product.setProductPrice(productDTO.getProductPrice());
        product.setAvailableQuantity(productDTO.getAvailableQuantity());
        return product;
    }
    public static ProductDTO mapToProductDTO( Product product , ProductDTO productDTO) {
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductCategory(product.getProductCategory());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setAvailableQuantity(product.getAvailableQuantity());
        return productDTO;
    }

    public static ProductResponseDTO mapToProductResponseDTO(Product product , ProductResponseDTO productResponseDTO) {
        productResponseDTO.setProductId(product.getProductId());
        productResponseDTO.setProductName(product.getProductName());
        productResponseDTO.setProductDescription(product.getProductDescription());
        productResponseDTO.setProductCategory(product.getProductCategory());
        productResponseDTO.setProductPrice(product.getProductPrice());
        productResponseDTO.setAvailableQuantity(product.getAvailableQuantity());
        return  productResponseDTO;
    }
}
