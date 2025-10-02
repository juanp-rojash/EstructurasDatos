package com.example;

public class Persona implements Comparable<Persona>{

    private String Nombre;
    private int RangoUrgencia;

    public Persona(String nombre, int rangoUrgencia){

        Nombre = nombre;
        RangoUrgencia = rangoUrgencia;

    }

    public int getRangoUrgencia() {
        return RangoUrgencia;
    }

    @Override
    public int compareTo(Persona otroUsuario){

        int resultadoPrioridad = Integer.compare(RangoUrgencia, otroUsuario.getRangoUrgencia());

        return  resultadoPrioridad;

    }

    @Override
    public String toString(){

        return "\n - Usuario: " + Nombre + "\n *** Prioridad: " + RangoUrgencia + "\n";

    }
}
