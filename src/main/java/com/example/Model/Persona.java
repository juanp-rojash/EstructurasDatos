package com.example.Model;

public class Persona {

    private int Id;
    private String Nombre;
    private short Edad;

    public Persona(int id, String nombre, short edad){

        Id = id;
        Nombre = nombre;
        Edad = edad;

    }

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public short getEdad() {
        return Edad;
    }

    @Override
    public String toString() {
        return "Persona [Id=" + Id + ", Nombre=" + Nombre + ", Edad=" + Edad + "]";
    }

}
