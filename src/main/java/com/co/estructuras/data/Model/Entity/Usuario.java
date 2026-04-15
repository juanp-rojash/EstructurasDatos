package com.co.estructuras.data.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    private long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private int edad;

}
