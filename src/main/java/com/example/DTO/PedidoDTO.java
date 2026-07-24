package com.example.DTO;

import com.example.Model.Pedido;

import java.util.UUID;

/**
 * DTO (Data Transfer Object) que representa un pedido tal como viaja en el JSON.
 *
 * <p>Al igual que {@link UsuarioDTO}, es un {@code record} inmutable cuyo objetivo
 * es servir de puente entre el JSON y el modelo de dominio {@link Pedido}. Contiene
 * un {@link UsuarioDTO} anidado como cliente, reflejando la misma estructura jerárquica
 * que el JSON de entrada.</p>
 *
 * <p>Las conversiones se aplican en cascada: al transformar un {@code PedidoDTO} también
 * se transforma el {@code UsuarioDTO} que contiene, y viceversa.</p>
 *
 * @param Id      identificador único del pedido
 * @param Cliente DTO del usuario que realizó el pedido
 * @param Precio  precio total del pedido
 * @see Pedido
 * @see UsuarioDTO
 */
public record PedidoDTO(UUID Id, UsuarioDTO Cliente, float Precio) {

    /**
     * Convierte este DTO en su modelo de dominio equivalente.
     *
     * <p>La conversión es en cascada: además de crear el {@link Pedido}, transforma
     * el {@link UsuarioDTO} anidado en su {@link com.example.Model.Usuario} de dominio.</p>
     *
     * @return un nuevo {@link Pedido} con los mismos datos que este DTO
     */
    public Pedido DTO_Modelo(){ return new Pedido( this.Id, this.Cliente.DTO_Modelo(), this.Precio ); }

    /**
     * Crea un DTO a partir de un modelo de dominio (operación inversa a
     * {@link #DTO_Modelo()}).
     *
     * <p>Método estático de fábrica que también convierte en cascada el cliente
     * ({@link com.example.Model.Usuario}) del pedido a su {@link UsuarioDTO}.</p>
     *
     * @param pedido el {@link Pedido} de dominio que se desea convertir
     * @return un nuevo {@link PedidoDTO} con los mismos datos que el modelo
     */
    public static PedidoDTO Modelo_DTO( Pedido pedido ){ return new PedidoDTO( pedido.getId(), UsuarioDTO.Modelo_DTO(pedido.getCliente()), pedido.getPrecio() ); }

}
