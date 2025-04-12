package com.onboarding.simulator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Onboarding Simulation System API")
                        .version("1.0.0")
                        .description("API for simulating digital onboarding processes for integration testing")
                        .contact(new Contact()
                                .name("Development Team")
                                .email("dev@example.com")));
    }
}