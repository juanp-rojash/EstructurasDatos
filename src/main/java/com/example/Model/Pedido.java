package com.example.Model;

import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio que representa un pedido realizado por un cliente.
 *
 * <p>Un pedido agrupa tres datos: un identificador único ({@link UUID}), el
 * {@link Usuario} que lo realizó y el precio total. Al igual que {@link Usuario},
 * es una entidad de dominio inmutable de cara al exterior (solo getters).</p>
 *
 * <p>Nótese que el atributo {@code Cliente} es a su vez un objeto {@link Usuario},
 * lo que refleja una relación de composición: un pedido "contiene" un usuario.</p>
 *
 * @see com.example.DTO.PedidoDTO
 * @see Usuario
 */
public class Pedido {

    /** Identificador único universal (UUID) del pedido. */
    private UUID Id;

    /** Usuario que realizó el pedido. */
    private Usuario Cliente;

    /** Precio total del pedido. */
    private float Precio;

    /**
     * Construye un nuevo pedido con su identificador, cliente y precio.
     *
     * @param id      identificador único del pedido
     * @param cliente usuario que realiza el pedido
     * @param precio  precio total del pedido
     */
    public Pedido(UUID id, Usuario cliente, float precio) {
        Id = id;
        Cliente = cliente;
        Precio = precio;
    }

    /**
     * Devuelve el identificador único del pedido.
     *
     * @return el {@link UUID} del pedido
     */
    public UUID getId() {
        return Id;
    }

    /**
     * Devuelve el cliente asociado al pedido.
     *
     * @return el {@link Usuario} que realizó el pedido
     */
    public Usuario getCliente() {
        return Cliente;
    }

    /**
     * Devuelve el precio total del pedido.
     *
     * @return el precio como número de punto flotante
     */
    public float getPrecio() {
        return Precio;
    }

    /**
     * Genera una representación textual legible del pedido, útil para depuración
     * y para imprimir en consola. Incluye la representación del cliente asociado.
     *
     * @return una cadena con los valores de los atributos
     */
    @Override
    public String toString() {
        return "Pedido {" +
                "Id = " + Id +
                ", Cliente = " + Cliente +
                ", Precio = " + Precio +
                '}';
    }

    /**
     * Compara este pedido con otro objeto para determinar si son "iguales" por
     * valor. Dos pedidos son iguales si coinciden su identificador, su cliente
     * y su precio.
     *
     * @param object el objeto a comparar con este pedido
     * @return {@code true} si ambos representan el mismo pedido; {@code false} en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Pedido pedido)) return false;
        return Float.compare(Precio, pedido.Precio) == 0 && Objects.equals(Id, pedido.Id) && Objects.equals(Cliente, pedido.Cliente);
    }

    /**
     * Calcula el código hash del pedido a partir de sus atributos, de forma
     * coherente con {@link #equals(Object)} para su uso en colecciones basadas
     * en hash.
     *
     * @return el código hash calculado
     */
    @Override
    public int hashCode() {
        return Objects.hash(Id, Cliente, Precio);
    }
}
