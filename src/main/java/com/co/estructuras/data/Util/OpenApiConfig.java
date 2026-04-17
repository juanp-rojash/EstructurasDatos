package com.co.estructuras.data.Util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Usuarios")
                        .version("1.0.0")
                        .description("Documentación de los servicios REST para la administración de usuarios del sistema.")
                        .contact(new Contact()
                                .name("Ingeniería y Desarrollo")
                                .email("soporte@ejemplo.com")));
    }
}
