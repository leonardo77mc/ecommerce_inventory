package com.ecommerce_inventory.product.application;

import com.ecommerce_inventory.product.infrastructure.external.ExternalProductDto;
import com.ecommerce_inventory.product.infrastructure.external.ExternalProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Use case for updating product stock in external microservice.
 * This class encapsulates the business logic for updating
 * product stock by communicating with an external product service.
 */
@Service
public class UpdateProductStockUseCase {
    
    private final ExternalProductService externalProductService;
    
    public UpdateProductStockUseCase(ExternalProductService externalProductService) {
        this.externalProductService = externalProductService;
    }
    
    /**
     * Executes the use case to update product stock in external service
     * @param productId the product ID to update
     * @param newStock the new stock amount
     * @return Optional containing the updated product if successful, empty otherwise
     * @throws IllegalArgumentException if the ID is null or invalid, or if stock is negative
     */
    public Optional<ExternalProductDto> execute(Long productId, Integer newStock) {
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive number");
        }
        
        if (newStock == null || newStock < 0) {
            throw new IllegalArgumentException("Stock must be a non-negative number");
        }
        
        return externalProductService.updateProductStock(productId, newStock);
    }
}