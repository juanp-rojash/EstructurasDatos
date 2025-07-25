package adtrepaso.configuration;

import java.util.ArrayList;
import java.util.List;

import adtrepaso.model.Pedido.Pedido;

public class MainDao {


    private static MainDao instancia;
    private List<Pedido> pedidos;
    
    private MainDao(){
        pedidos = new ArrayList<Pedido>();
    }

    public static MainDao getInstance(){

        if(instancia == null){
            instancia = new MainDao();
        }
        // retorno
        return instancia;

    }

    public void agregarPedido(Pedido pedido){

        /*TODO: Validar que el pedido no llegue null */

        pedidos.add(pedido);
    }

    public List<Pedido> getPedido(){ return pedidos; }

}
