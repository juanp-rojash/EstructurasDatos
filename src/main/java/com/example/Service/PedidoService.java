package com.example.Service;

import com.example.Model.Pedido;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que centraliza las operaciones de análisis sobre una colección de pedidos.
 *
 * <p>Una clase de servicio (capa Service) es donde vive la <b>lógica de negocio</b>:
 * operaciones que analizan, filtran, transforman o calculan sobre los datos. Se
 * separa a propósito de las clases de modelo (que solo almacenan datos) y de la
 * clase {@code Main} (que solo orquesta el flujo del programa).</p>
 *
 * <p>Este servicio recibe la lista de pedidos en su constructor y la guarda como
 * estado interno, de modo que todos sus métodos operan sobre esa misma colección
 * sin necesidad de pasarla como parámetro cada vez.</p>
 *
 * @see Pedido
 */
public class PedidoService {

    /** Colección de pedidos sobre la que operan todos los métodos del servicio. */
    private final List<Pedido> pedidos;

    /**
     * Construye el servicio a partir de la lista de pedidos que se desea analizar.
     *
     * @param pedidos la lista de pedidos a gestionar; no debe ser {@code null}
     */
    public PedidoService(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


    /**
     * Devuelve todos los pedidos cuyo cliente tenga la identificación indicada.
     *
     * <p>Complementa a {@link #buscarPorIdentificacionUsuario(String)} para el caso
     * en que un mismo usuario tenga varios pedidos.</p>
     *
     * @param identificacion identificación del usuario a buscar
     * @return una lista (posiblemente vacía) con todos los pedidos de ese usuario
     */
    public List<Pedido> buscarTodosPorIdentificacionUsuario(String identificacion) {
        return pedidos.stream()
                .filter(pedido -> pedido.getCliente() != null
                        && pedido.getCliente().getIdentificacion().equals(identificacion))
                .toList();
    }

    /**
     * Calcula la suma de los precios de todos los pedidos de la lista.
     *
     * <p>El resultado se devuelve como {@code double} para reducir los errores de
     * redondeo que se acumularían al sumar muchos valores {@code float}.</p>
     *
     * @return el total acumulado de todos los precios; {@code 0.0} si la lista está vacía
     */
    public double totalizarPedidos() {
        return pedidos.stream()
                .mapToDouble(Pedido::getPrecio)
                .sum();
    }

    /**
     * Devuelve la cantidad de pedidos gestionados por el servicio.
     *
     * @return el número de pedidos en la lista
     */
    public int contarPedidos() {
        return pedidos.size();
    }
}
