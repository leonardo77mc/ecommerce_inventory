package com.ecommerce_inventory.product.infrastructure.external;

/**
 * DTO for external product service response.
 * This record represents the JSON structure
 * returned by the external product microservice.
 */
public record ExternalProductDto(
    Long id,
    String name,
    String description,
    Double price,
    Integer stock,
    String category,
    boolean available
) {
}