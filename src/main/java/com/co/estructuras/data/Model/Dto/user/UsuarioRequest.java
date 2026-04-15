package com.co.estructuras.data.Model.Dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @Email(message = "El email debe ser valido")
        String email,

        @NotBlank
        String password,

        @Min(value = 18, message = "La edad debe ser mayor a 18")
        @Max(value = 120, message = "La edad debe ser menor a 120")
        int edad

) { }
