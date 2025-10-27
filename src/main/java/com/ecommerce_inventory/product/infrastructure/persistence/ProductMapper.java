package com.ecommerce_inventory.product.infrastructure.persistence;

import com.ecommerce_inventory.product.domain.Product;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert between domain Product and ProductEntity.
 * This class handles the transformation between the domain model
 * and the persistence model, maintaining separation of concerns.
 */
@Component
public class ProductMapper {
    
    /**
     * Converts a domain Product to a ProductEntity
     * @param product the domain product
     * @return the corresponding ProductEntity
     */
    public ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        
        return new ProductEntity(
            product.id(),
            product.name(),
            product.description(),
            product.price(),
            product.stock(),
            product.category()
        );
    }
    
    /**
     * Converts a ProductEntity to a domain Product
     * @param entity the ProductEntity
     * @return the corresponding domain Product
     */
    public Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            entity.getStock(),
            entity.getCategory()
        );
    }
}