package adtrepaso.model.Pedido;

import java.sql.Date;
import java.util.UUID;

import adtrepaso.model.Cliente.Cliente;

public class Pedido {

    private UUID id;
    private String descripcion;
    private double precio;
    private Cliente cliente;
    private Date fechaCreacion;

    public Pedido(String descripcion, double precio, Cliente cliente, Date fecha){

        this.id = UUID.randomUUID();
        this.descripcion = descripcion;
        this.precio = precio;
        this.cliente = cliente;
        this.fechaCreacion = fecha;

    }

    public UUID getId(){ return this.id; }
    public String getDescripcion(){ return this.descripcion; }
    public double getPrecio(){ return this.precio; }
    public Cliente getCliente(){ return this.cliente; }
    public Date getFecha(){ return this.fechaCreacion; }

    @Override
    public String toString(){
        return 
        "Pedido: " + id + "\n * Descripcion: " + descripcion + "\n * Precio: " + precio + 
        "\n* Cliente" + cliente + "\n* Fecha: " + fechaCreacion;
    }

}
