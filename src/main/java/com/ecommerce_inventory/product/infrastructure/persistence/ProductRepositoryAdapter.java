package com.ecommerce_inventory.product.infrastructure.persistence;

import com.ecommerce_inventory.product.domain.Product;
import com.ecommerce_inventory.product.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter implementation of ProductRepository.
 * This class implements the domain repository interface
 * and adapts it to work with JPA repositories.
 */
@Repository
public class ProductRepositoryAdapter implements ProductRepository {
    
    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;
    
    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository, 
                                  ProductMapper productMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.productMapper = productMapper;
    }
    
    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll()
                .stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(productMapper::toDomain);
    }
    
    @Override
    public List<Product> findByCategory(String category) {
        return jpaProductRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Product save(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        ProductEntity savedEntity = jpaProductRepository.save(entity);
        return productMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaProductRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaProductRepository.existsById(id);
    }
}