package com.ecommerce_inventory.product.infrastructure.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Service for communicating with external product microservice.
 * This service handles HTTP requests to retrieve product information
 * from another microservice.
 */
@Service
public class ExternalProductService {
    
    private final RestTemplate restTemplate;
    private final String baseUrl;
    
    public ExternalProductService(RestTemplate restTemplate, 
                                @Value("${external.product.service.url:http://localhost:8088}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }
    
    /**
     * Retrieves product information from external microservice
     * @param productId the product ID to search for
     * @return Optional containing the external product if found, empty otherwise
     */
    public Optional<ExternalProductDto> getProductById(Long productId) {
        try {
            String url = baseUrl + "/api/products/" + productId;
            ExternalProductDto product = restTemplate.getForObject(url, ExternalProductDto.class);
            return Optional.ofNullable(product);
        } catch (RestClientException e) {
            // Log the error in a real application
            System.err.println("Error calling external product service: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    /**
     * Gets the available stock for a specific product
     * @param productId the product ID
     * @return Optional containing the stock amount if product exists, empty otherwise
     */
    public Optional<Integer> getProductStock(Long productId) {
        return getProductById(productId)
                .map(ExternalProductDto::stock);
    }
    
    /**
     * Updates a product in the external microservice
     * @param productId the product ID to update
     * @param updateProductDto the product data to update
     * @return Optional containing the updated product if successful, empty otherwise
     */
    public Optional<ExternalProductDto> updateProduct(Long productId, UpdateProductDto updateProductDto) {
        try {
            String url = baseUrl + "/api/products/" + productId;
            HttpEntity<UpdateProductDto> requestEntity = new HttpEntity<>(updateProductDto);
            ResponseEntity<ExternalProductDto> response = restTemplate.exchange(
                url, 
                HttpMethod.PUT, 
                requestEntity, 
                ExternalProductDto.class
            );
            return Optional.ofNullable(response.getBody());
        } catch (RestClientException e) {
            // Log the error in a real application
            System.err.println("Error updating product in external service: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    /**
     * Updates only the stock of a specific product
     * @param productId the product ID
     * @param newStock the new stock amount
     * @return Optional containing the updated product if successful, empty otherwise
     */
    public Optional<ExternalProductDto> updateProductStock(Long productId, Integer newStock) {
        UpdateProductDto updateDto = UpdateProductDto.withStock(newStock);
        return updateProduct(productId, updateDto);
    }
}