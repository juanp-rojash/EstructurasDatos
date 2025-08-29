package com.example.Model;

import java.util.LinkedList;
import java.util.UUID;

public class Transaccion {

    private UUID Id;
    private Persona Usuario;
    private String Clasificacion;
    private double Monto;
    private LinkedList<Producto> Compra;

    public Transaccion(Persona usuario, String clasificacion, double monto, LinkedList<Producto> compra) {
        Id = UUID.randomUUID();
        Usuario = usuario;
        Clasificacion = clasificacion;
        Monto = monto;
        Compra = compra;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public Object getPersona() {
        return Usuario;
    }

    @Override
    public String toString() {
        return "Transaccion [Id=" + Id + ", Usuario=" + Usuario + ", Clasificacion=" + Clasificacion + ", Monto=" + Monto
                + ", Compra=" + Compra + "]";
    }


}
