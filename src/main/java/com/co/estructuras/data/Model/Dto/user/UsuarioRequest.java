package com.co.estructuras.data.Model.Dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "UsuarioRequest",
        description = "DTO para solicitar la creación de un nuevo usuario",
        example = "{\"nombre\":\"Juan\",\"apellido\":\"Pérez\",\"email\":\"juan@example.com\",\"password\":\"MiPassword123\",\"edad\":25}"
)
public record UsuarioRequest(

        @Schema(
                description = "Nombre del usuario",
                example = "Juan",
                minLength = 1
        )
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "Pérez",
                minLength = 1
        )
        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @Schema(
                description = "Email del usuario (debe ser único y válido)",
                example = "juan@example.com",
                format = "email"
        )
        @Email(message = "El email debe ser valido")
        String email,

        @Schema(
                description = "Contraseña del usuario (mínimo 8 caracteres)",
                example = "MiPassword123",
                minLength = 8
        )
        @NotBlank
        String password,

        @Schema(
                description = "Edad del usuario (debe estar entre 18 y 120 años)",
                example = "25",
                minimum = "18",
                maximum = "120"
        )
        @Min(value = 18, message = "La edad debe ser mayor a 18")
        @Max(value = 120, message = "La edad debe ser menor a 120")
        int edad

) { }
