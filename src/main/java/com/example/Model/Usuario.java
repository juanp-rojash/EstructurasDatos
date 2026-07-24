package com.example.Model;

import java.util.Objects;

/**
 * Modelo de dominio que representa a un usuario (cliente) dentro de la aplicación.
 *
 * <p>Una clase de modelo (o entidad de dominio) contiene la lógica y los datos
 * "reales" que la aplicación manipula internamente, a diferencia de un DTO, que
 * solo sirve para transportar datos hacia/desde formatos externos como JSON.</p>
 *
 * <p>Esta clase es inmutable de cara al exterior: sus atributos son privados y
 * únicamente se exponen mediante métodos de lectura (getters); no hay setters.</p>
 *
 * @see com.example.DTO.UsuarioDTO
 */
public class Usuario {

    /** Documento o identificador único del usuario (por ejemplo, la cédula). */
    private String Identificacion;

    /** Nombre completo del usuario. */
    private String Nombre;

    /**
     * Construye un nuevo usuario con su identificación y nombre.
     *
     * @param identificacion documento o identificador único del usuario
     * @param nombre         nombre completo del usuario
     */
    public Usuario(String identificacion, String nombre) {
        Identificacion = identificacion;
        Nombre = nombre;
    }

    /**
     * Devuelve la identificación del usuario.
     *
     * @return el documento o identificador único
     */
    public String getIdentificacion() {
        return Identificacion;
    }

    /**
     * Devuelve el nombre del usuario.
     *
     * @return el nombre completo
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Genera una representación textual legible del usuario, útil para depuración
     * y para imprimir en consola.
     *
     * @return una cadena con los valores de los atributos
     */
    @Override
    public String toString() {
        return "Usuario {" +
                "Identificacion = '" + Identificacion + '\'' +
                ", Nombre = '" + Nombre + '\'' +
                '}';
    }

    /**
     * Compara este usuario con otro objeto para determinar si son "iguales" por
     * valor. Dos usuarios se consideran iguales si tienen la misma identificación
     * y el mismo nombre (no importa si son instancias distintas en memoria).
     *
     * @param object el objeto a comparar con este usuario
     * @return {@code true} si ambos representan el mismo usuario; {@code false} en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Usuario usuario)) return false;
        return Objects.equals(Identificacion, usuario.Identificacion) && Objects.equals(Nombre, usuario.Nombre);
    }

    /**
     * Calcula el código hash del usuario a partir de sus atributos. Es coherente
     * con {@link #equals(Object)}: dos usuarios iguales producen el mismo hash,
     * lo que permite usarlos correctamente en colecciones como {@code HashSet} o
     * {@code HashMap}.
     *
     * @return el código hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(Identificacion, Nombre);
    }
}
