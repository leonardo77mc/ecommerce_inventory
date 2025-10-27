package com.ecommerce_inventory.product.domain;

import java.util.List;
import java.util.Optional;

/**
 * Repository port for Product domain entity.
 * This interface defines the contract for data access operations
 * without depending on any specific implementation.
 */
public interface ProductRepository {
    
    /**
     * Finds all products in the system
     * @return List of all products
     */
    List<Product> findAll();
    
    /**
     * Finds a product by its ID
     * @param id the product ID
     * @return Optional containing the product if found, empty otherwise
     */
    Optional<Product> findById(Long id);
    
    /**
     * Finds products by category
     * @param category the product category
     * @return List of products in the specified category
     */
    List<Product> findByCategory(String category);
    
    /**
     * Saves a product
     * @param product the product to save
     * @return the saved product
     */
    Product save(Product product);
    
    /**
     * Deletes a product by its ID
     * @param id the product ID to delete
     */
    void deleteById(Long id);
    
    /**
     * Checks if a product exists by ID
     * @param id the product ID
     * @return true if the product exists, false otherwise
     */
    boolean existsById(Long id);
}