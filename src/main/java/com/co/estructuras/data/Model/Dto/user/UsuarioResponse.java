package com.co.estructuras.data.Model.Dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "UsuarioResponse",
        description = "DTO que contiene la respuesta con los datos del usuario creado o recuperado",
        example = "{\"id\":1,\"nombre\":\"Juan\",\"apellido\":\"Pérez\",\"email\":\"juan@example.com\"}"
)
public record UsuarioResponse(

        @Schema(
                description = "Identificador único del usuario",
                example = "1"
        )
        String id,

        @Schema(
                description = "Nombre del usuario",
                example = "Juan"
        )
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "Pérez"
        )
        String apellido,

        @Schema(
                description = "Email del usuario",
                example = "juan@example.com",
                format = "email"
        )
        String email

) { }
