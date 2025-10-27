package com.ecommerce_inventory.product.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA Repository interface for ProductEntity.
 * This interface extends JpaRepository to provide
 * standard CRUD operations and custom queries.
 */
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    
    /**
     * Finds products by category
     * @param category the category to search for
     * @return List of products in the specified category
     */
    List<ProductEntity> findByCategory(String category);
    
    /**
     * Finds products by category ignoring case
     * @param category the category to search for
     * @return List of products in the specified category
     */
    List<ProductEntity> findByCategoryIgnoreCase(String category);
}