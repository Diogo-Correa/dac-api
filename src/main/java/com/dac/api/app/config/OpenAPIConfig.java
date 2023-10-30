package com.dac.api.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${api.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setName("GitHub");
        contact.setUrl("https://github.com/Diogo-Correa/dac-api/");

        Info info = new Info()
                .title("Projeto e Implementação de uma API REST")
                .version("1.0")
                .contact(contact)
                .description("Essa API possui endpoints para gerenciar um sismeta de eventos para computação.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}