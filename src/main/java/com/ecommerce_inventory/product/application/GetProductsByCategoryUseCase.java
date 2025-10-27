package com.ecommerce_inventory.product.application;

import com.ecommerce_inventory.product.domain.Product;
import com.ecommerce_inventory.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Use case for retrieving products by category.
 * This demonstrates how business logic can be encapsulated
 * in specific use cases within the application layer.
 */
@Service
public class GetProductsByCategoryUseCase {
    
    private final ProductRepository productRepository;
    
    public GetProductsByCategoryUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    /**
     * Executes the use case to get products by category
     * @param category the category to filter by
     * @return List of products in the specified category
     */
    public List<Product> execute(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        
        return productRepository.findByCategory(category.trim());
    }
}