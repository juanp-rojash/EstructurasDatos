package com.example.Service;

import com.example.Model.Pedido;
import com.example.Model.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoServiceTest {

    private List<Pedido> samplePedidos() {
        Usuario u1 = new Usuario("123", "Alice");
        Usuario u2 = new Usuario("456", "Bob");
        Pedido p1 = new Pedido(UUID.randomUUID(), u1, 10.5f);
        Pedido p2 = new Pedido(UUID.randomUUID(), u1, 5.5f);
        Pedido p3 = new Pedido(UUID.randomUUID(), u2, 7.0f);
        return Arrays.asList(p1, p2, p3);
    }

    @Test
    public void testTotalizarPedidos() {
        List<Pedido> pedidos = samplePedidos();
        PedidoService service = new PedidoService(pedidos);
        double expected = pedidos.stream().mapToDouble(Pedido::getPrecio).sum();
        double actual = service.totalizarPedidos();
        assertEquals(expected, actual, 1e-6);
    }

    @Test
    public void testBuscarTodosPorIdentificacionUsuario() {
        List<Pedido> pedidos = samplePedidos();
        PedidoService service = new PedidoService(pedidos);
        List<Pedido> encontrados = service.buscarTodosPorIdentificacionUsuario("123");
        assertEquals(2, encontrados.size());
        assertTrue(encontrados.stream().allMatch(p -> p.getCliente() != null && "123".equals(p.getCliente().getIdentificacion())));
    }
}
