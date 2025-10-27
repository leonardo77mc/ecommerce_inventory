package com.ecommerce_inventory.product.domain;

/**
 * Product domain entity representing a product in the inventory system.
 * This is the core business entity that encapsulates the product data and business rules.
 */
public record Product(
    Long id,
    String name,
    String description,
    Double price,
    Integer stock,
    String category
) {
    
    /**
     * Validates that the product has valid business rules
     */
    public Product {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Product price cannot be null or negative");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Product stock cannot be null or negative");
        }
    }
    
    /**
     * Checks if the product is available (has stock)
     */
    public boolean isAvailable() {
        return stock > 0;
    }
    
    /**
     * Checks if the product is out of stock
     */
    public boolean isOutOfStock() {
        return stock == 0;
    }
}