package com.ecommerce_inventory.product.application;

import com.ecommerce_inventory.product.infrastructure.external.ExternalProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Use case for retrieving product availability from external microservice.
 * This class encapsulates the business logic for checking product stock
 * by communicating with an external product service.
 */
@Service
public class GetProductAvailabilityUseCase {
    
    private final ExternalProductService externalProductService;
    
    public GetProductAvailabilityUseCase(ExternalProductService externalProductService) {
        this.externalProductService = externalProductService;
    }
    
    /**
     * Executes the use case to get product availability from external service
     * @param productId the product ID to check availability for
     * @return Optional containing the stock amount if product exists, empty otherwise
     * @throws IllegalArgumentException if the ID is null or invalid
     */
    public Optional<Integer> execute(Long productId) {
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive number");
        }
        
        return externalProductService.getProductStock(productId);
    }
}