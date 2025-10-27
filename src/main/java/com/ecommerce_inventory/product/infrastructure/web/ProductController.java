package com.ecommerce_inventory.product.infrastructure.web;

import com.ecommerce_inventory.product.application.GetAllProductsUseCase;
import com.ecommerce_inventory.product.application.GetProductByIdUseCase;
import com.ecommerce_inventory.product.application.GetProductsByCategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final GetProductsByCategoryUseCase getProductsByCategoryUseCase;
    
    public ProductController(GetAllProductsUseCase getAllProductsUseCase,
                           GetProductByIdUseCase getProductByIdUseCase,
                           GetProductsByCategoryUseCase getProductsByCategoryUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.getProductsByCategoryUseCase = getProductsByCategoryUseCase;
    }
    
    /**
     * Gets all products in the system
     * @return List of all products
     */
    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves all products in the inventory")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = getAllProductsUseCase.execute()
                .stream()
                .map(ProductResponse::from)
                .toList();
        
        return ResponseEntity.ok(products);
    }
    
    /**
     * Gets a specific product by its ID
     * @param id the product ID
     * @return The product if found, 404 if not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a specific product by its unique identifier")
    public ResponseEntity<ProductResponse> getProductById(
            @Parameter(description = "Product ID", example = "1")
            @PathVariable Long id) {
        
        Optional<ProductResponse> product = getProductByIdUseCase.execute(id)
                .map(ProductResponse::from);
        
        return product.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Gets products by category
     * @param category the category to filter by
     * @return List of products in the specified category
     */
    @GetMapping("/category/{category}")
    @Operation(summary = "Get products by category", description = "Retrieves all products in a specific category")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(
            @Parameter(description = "Product category", example = "electronics")
            @PathVariable String category) {
        
        List<ProductResponse> products = getProductsByCategoryUseCase.execute(category)
                .stream()
                .map(ProductResponse::from)
                .toList();
        
        return ResponseEntity.ok(products);
    }
}