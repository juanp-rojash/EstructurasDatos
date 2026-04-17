package com.co.estructuras.data.Model.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "usuarios")
@Schema(
        name = "Usuario",
        description = "Entidad que representa un usuario del sistema"
)
public class Usuario {

    @Schema(
            description = "Identificador único del usuario",
            example = "1"
    )
    @Id
    private String id;

    @Schema(
            description = "Nombre del usuario",
            example = "Juan"
    )
    private String nombre;

    @Schema(
            description = "Apellido del usuario",
            example = "Pérez"
    )
    private String apellido;

    @Schema(
            description = "Email del usuario",
            example = "juan@example.com",
            format = "email"
    )
    private String email;

    @Schema(
            description = "Contraseña del usuario (encriptada)",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String password;

    @Schema(
            description = "Edad del usuario",
            example = "25",
            minimum = "18",
            maximum = "120"
    )
    private int edad;

}
