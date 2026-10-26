package com.example.book_review_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookReviewOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book Review API")
                        .description("A small REST API for managing users, books and reviews.")
                        .version("v1.0.0"));
    }
}
