package com.ecommerce_inventory.product.infrastructure.web;

/**
 * Response DTO for Product Availability API endpoint.
 * This record represents the JSON structure
 * returned by the availability endpoint.
 */
public record ProductAvailabilityResponse(
    Long productId,
    Integer availableStock,
    boolean inStock
) {
    
    /**
     * Creates a ProductAvailabilityResponse from product ID and stock
     * @param productId the product ID
     * @param stock the available stock amount
     * @return the corresponding ProductAvailabilityResponse
     */
    public static ProductAvailabilityResponse from(Long productId, Integer stock) {
        return new ProductAvailabilityResponse(
            productId,
            stock,
            stock != null && stock > 0
        );
    }
}