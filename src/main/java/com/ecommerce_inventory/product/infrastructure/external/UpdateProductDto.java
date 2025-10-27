package com.ecommerce_inventory.product.infrastructure.external;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO for updating product information in external microservice.
 * This record represents the JSON structure for PUT requests
 * to update product data in the external service.
 * All fields are optional to allow partial updates.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateProductDto(
    String name,
    String description,
    Double price,
    Integer stock,
    String category
) {
    
    /**
     * Creates an UpdateProductDto with only stock update
     * @param stock the new stock amount
     * @return UpdateProductDto with only stock field set
     */
    public static UpdateProductDto withStock(Integer stock) {
        return new UpdateProductDto(null, null, null, stock, null);
    }
    
    /**
     * Creates an UpdateProductDto with all fields
     * @param name product name
     * @param description product description
     * @param price product price
     * @param stock product stock
     * @param category product category
     * @return UpdateProductDto with all fields set
     */
    public static UpdateProductDto withAllFields(String name, String description, Double price, Integer stock, String category) {
        return new UpdateProductDto(name, description, price, stock, category);
    }
}