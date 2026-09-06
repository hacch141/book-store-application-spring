package com.harsh.bookstore.catalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfiguration {

    @Bean
    OpenAPI catalogOpenApi(@Value("${catalog.api-gateway-url}") String apiGatewayUrl) {
        return new OpenAPI()
                .info(new Info()
                        .title("Catalog Service API")
                        .description("Read-only APIs for the bookstore product catalog")
                        .version("v1"))
                .servers(List.of(new Server().url(apiGatewayUrl).description("API Gateway")));
    }
}
