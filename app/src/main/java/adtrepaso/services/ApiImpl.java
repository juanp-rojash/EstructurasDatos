package adtrepaso.services;

import adtrepaso.configuration.MainDao;
import adtrepaso.model.Pedido.Pedido;

import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

public class ApiImpl implements Iapi{

    private MainDao instancia;

    public ApiImpl(){
        this.instancia = MainDao.getInstance();
    }

    @Override
    public String pedidosPorCliente(String identificacion){

        Gson gson = new Gson();

        try {

            String mensaje = "";
            
            List<Pedido> pedidosFiltrados = instancia.getPedido().stream()
            .filter(p -> p.getCliente().getIdentificacion().equals(identificacion)).toList();

            mensaje = gson.toJson(pedidosFiltrados);

            return mensaje;
        } 
        catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }

    }

    @Override
    public String pedidosPorFecha(Date fecha){

        Gson gson = new Gson();

        try {

            String mensaje = "";
            
            List<Pedido> pedidosFiltrados = instancia.getPedido().stream()
            .filter(p -> p.getFecha().equals(fecha)).toList();

            mensaje = gson.toJson(pedidosFiltrados);

            return mensaje;
        } 
        catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }

    }

    @Override
    public String pedidosPorPrecio(double precioMinimo, double precioMaximo){

        Gson gson = new Gson();

        try {

            String mensaje = "";
            
            List<Pedido> pedidosFiltrados = instancia.getPedido().stream()
            .filter(p -> p.getPrecio() >= precioMinimo && p.getPrecio() <= precioMaximo).toList();

            mensaje = gson.toJson(pedidosFiltrados);

            return mensaje;
        } 
        catch (Exception e) {
            return gson.toJson("Error: " + e.getMessage());
        }

    }

}
