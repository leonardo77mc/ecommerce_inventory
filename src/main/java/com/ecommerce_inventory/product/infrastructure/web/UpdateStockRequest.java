package com.ecommerce_inventory.product.infrastructure.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for updating product stock.
 * This record represents the JSON structure
 * expected by the stock update endpoint.
 */
@Schema(description = "Request for updating product stock")
public record UpdateStockRequest(
    @Schema(description = "New stock quantity", example = "14", minimum = "0")
    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock must be non-negative")
    Integer stock
) {
}