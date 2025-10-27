package com.ecommerce_inventory.product.application;

import com.ecommerce_inventory.product.domain.Product;
import com.ecommerce_inventory.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Use case for retrieving a product by its ID.
 * This class encapsulates the business logic for finding a specific product
 * and orchestrates the interaction with the domain layer.
 */
@Service
public class GetProductByIdUseCase {
    
    private final ProductRepository productRepository;
    
    public GetProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    /**
     * Executes the use case to get a product by its ID
     * @param id the product ID to search for
     * @return Optional containing the product if found, empty otherwise
     * @throws IllegalArgumentException if the ID is null or invalid
     */
    public Optional<Product> execute(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive number");
        }
        
        return productRepository.findById(id);
    }
}