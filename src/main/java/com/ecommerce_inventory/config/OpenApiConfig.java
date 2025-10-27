package com.ecommerce_inventory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for the E-commerce Inventory API.
 * This configuration provides metadata for the API documentation.
 */
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-commerce Inventory API")
                        .version("1.0.0")
                        .description("API for managing e-commerce inventory using Clean/Hexagonal Architecture")
                        .contact(new Contact()
                                .name("Development Team")
                                .email("dev@ecommerce.com")));
    }
}