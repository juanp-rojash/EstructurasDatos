package com.example.DTO;

import com.example.Model.Usuario;

/**
 * DTO (Data Transfer Object) que representa un usuario tal como viaja en el JSON.
 *
 * <p>Un DTO es un objeto "puente" cuyo único propósito es transportar datos entre
 * capas o formatos (en este proyecto, entre el JSON y el modelo de dominio). Al ser
 * un {@code record} de Java, obtiene automáticamente el constructor, los métodos de
 * acceso ({@code Identificacion()}, {@code Nombre()}), {@code equals}, {@code hashCode}
 * y {@code toString}, lo que lo hace ideal para estructuras de datos simples e inmutables.</p>
 *
 * <p>Los nombres de sus componentes coinciden con las claves del JSON, de modo que la
 * librería Jackson pueda mapearlos de forma automática durante la deserialización.</p>
 *
 * @param Identificacion documento o identificador único del usuario
 * @param Nombre         nombre completo del usuario
 * @see Usuario
 */
public record UsuarioDTO(String Identificacion, String Nombre) {

    /**
     * Convierte este DTO en su modelo de dominio equivalente.
     *
     * <p>Se usa tras leer el JSON, para pasar del objeto de transporte al objeto
     * con el que trabaja internamente la aplicación.</p>
     *
     * @return un nuevo {@link Usuario} con los mismos datos que este DTO
     */
    public Usuario DTO_Modelo(){ return new Usuario(this.Identificacion, this.Nombre); }

    /**
     * Crea un DTO a partir de un modelo de dominio (operación inversa a
     * {@link #DTO_Modelo()}).
     *
     * <p>Es un método estático de fábrica: se usa antes de serializar a JSON, para
     * convertir el objeto de dominio en un objeto apto para el transporte.</p>
     *
     * @param usuario el {@link Usuario} de dominio que se desea convertir
     * @return un nuevo {@link UsuarioDTO} con los mismos datos que el modelo
     */
    public static UsuarioDTO Modelo_DTO(Usuario usuario){ return new UsuarioDTO(usuario.getIdentificacion(), usuario.getNombre()); }

}


