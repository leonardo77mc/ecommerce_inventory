package com.ecommerce_inventory.product.application;

import com.ecommerce_inventory.product.domain.Product;
import com.ecommerce_inventory.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Use case for retrieving all products.
 * This class encapsulates the business logic for listing products
 * and orchestrates the interaction with the domain layer.
 */
@Service
public class GetAllProductsUseCase {
    
    private final ProductRepository productRepository;
    
    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    /**
     * Executes the use case to get all products
     * @return List of all products in the system
     */
    public List<Product> execute() {
        return productRepository.findAll();
    }
}