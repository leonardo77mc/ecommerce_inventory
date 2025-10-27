package com.ecommerce_inventory.product.infrastructure.web;

import com.ecommerce_inventory.product.domain.Product;

/**
 * Response DTO for Product API endpoints.
 * This record represents the JSON structure
 * returned by the REST API.
 */
public record ProductResponse(
    Long id,
    String name,
    String description,
    Double price,
    Integer stock,
    String category,
    boolean available
) {
    
    /**
     * Creates a ProductResponse from a domain Product
     * @param product the domain product
     * @return the corresponding ProductResponse
     */
    public static ProductResponse from(Product product) {
        return new ProductResponse(
            product.id(),
            product.name(),
            product.description(),
            product.price(),
            product.stock(),
            product.category(),
            product.isAvailable()
        );
    }
}