package com.ecommerce_inventory.product.infrastructure.web;

import com.ecommerce_inventory.product.application.GetProductAvailabilityUseCase;
import com.ecommerce_inventory.product.application.UpdateProductStockUseCase;
import com.ecommerce_inventory.product.infrastructure.external.ExternalProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Product operations.
 * This controller handles HTTP requests and delegates
 * business logic to the application layer use cases.
 */
@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management operations")
public class ProductController {
    
    private final GetProductAvailabilityUseCase getProductAvailabilityUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    
    public ProductController(
            GetProductAvailabilityUseCase getProductAvailabilityUseCase,
            UpdateProductStockUseCase updateProductStockUseCase) {
        this.getProductAvailabilityUseCase = getProductAvailabilityUseCase;
        this.updateProductStockUseCase = updateProductStockUseCase;
    }

    
    /**
     * Gets the availability (stock) of a specific product from external microservice
     * @param id the product ID
     * @return The product availability information
     */
    @GetMapping("/{id}/availability")
    @Operation(summary = "Get product availability", description = "Retrieves the available stock of a product from external microservice")
    public ResponseEntity<ProductAvailabilityResponse> getProductAvailability(
            @Parameter(description = "Product ID", example = "2")
            @PathVariable Long id) {
        
        Optional<Integer> stock = getProductAvailabilityUseCase.execute(id);
        
        if (stock.isPresent()) {
            ProductAvailabilityResponse response = ProductAvailabilityResponse.from(id, stock.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Updates the stock of a product in the external microservice
     * @param id Product ID to update
     * @param request Request containing the new stock value
     * @return Updated product information or 404 if not found
     */
    @PutMapping("/{id}/stock")
    @Operation(summary = "Update product stock", description = "Updates the stock of a product in the external microservice")
    public ResponseEntity<ExternalProductDto> updateProductStock(
            @Parameter(description = "Product ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody UpdateStockRequest request) {
        
        Optional<ExternalProductDto> updatedProduct = updateProductStockUseCase.execute(id, request.stock());
        
        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}