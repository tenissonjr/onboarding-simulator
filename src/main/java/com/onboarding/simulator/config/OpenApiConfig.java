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
                        .title("Simulador API Valid")
                        .version("1.0.0")
                        .description("API para simulação do serviço de onboarding digital integrado ao sistema SIVIS")
                        .contact(new Contact()
                                .name("Tenisson Chaves Jr.")
                                .email("tenisson.junior@camara.leg.br")));
    }
}