package com.ecommerce_inventory.product.infrastructure.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for updating product stock.
 * This record represents the JSON structure
 * expected by the stock update endpoint.
 */
public record UpdateStockRequest(
    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock must be non-negative")
    Integer stock
) {
}