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
    
    
}