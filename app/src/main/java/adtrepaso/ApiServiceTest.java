package adtrepaso;

import adtrepaso.configuration.MainDao;
import adtrepaso.model.Cliente.Cliente;
import adtrepaso.model.Pedido.Pedido;
import adtrepaso.services.ApiImpl;
import adtrepaso.services.Iapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ApiServiceTest {

    public static void main(String[] args) {

        // Crear clientes
        Cliente cliente1 = new Cliente("Juan", "Perez", "juan.perez@example.com", "12345");
        Cliente cliente2 = new Cliente("Maria", "Lopez", "maria.lopez@example.com", "67890");
        Cliente cliente3 = new Cliente("Carlos", "Gomez", "carlos.gomez@example.com", "54321");

        // Crear pedidos
        Pedido pedido1 = new Pedido("Pedido 1", 100.0, cliente1, Date.valueOf("2025-07-20"));
        Pedido pedido2 = new Pedido("Pedido 2", 200.0, cliente2, Date.valueOf("2025-07-21"));
        Pedido pedido3 = new Pedido("Pedido 3", 150.0, cliente1, Date.valueOf("2025-07-22"));
        Pedido pedido4 = new Pedido("Pedido 4", 300.0, cliente3, Date.valueOf("2025-07-23"));

        // Simular almacenamiento de pedidos en MainDao
        MainDao mainDao = MainDao.getInstance();
        mainDao.agregarPedido(pedido1);
        mainDao.agregarPedido(pedido2);
        mainDao.agregarPedido(pedido3);
        mainDao.agregarPedido(pedido4);

        // Instanciar el servicio
        Iapi apiService = new ApiImpl();

        for(Pedido p : mainDao.getPedido()) {
            System.out.println(p);
        }

        System.out.println("\n\n\n\n");

        // Usar métodos del servicio
        System.out.println("Pedidos por cliente Juan:");
        System.out.println(apiService.pedidosPorCliente("12345"));

        System.out.println("\n\n\n\n");

        System.out.println("Pedidos por fecha 2025-07-21:");
        System.out.println(apiService.pedidosPorFecha(Date.valueOf("2025-07-21")));

        System.out.println("\n\n\n\n");

        System.out.println("Pedidos por precio entre 100 y 250:");
        System.out.println(apiService.pedidosPorPrecio(100.0, 250.0));
    }
}
