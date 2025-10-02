package com.example;

import java.time.LocalDateTime;

public class Tarea implements Comparable<Tarea> {

    private String Titulo;
    private int Prioridad;
    private LocalDateTime FechaIngreso;

    public Tarea(String titulo, int prioridad, LocalDateTime fechaIngreso){

        Titulo = titulo;
        Prioridad = prioridad;
        FechaIngreso = fechaIngreso;

    }

    public int getPrioridad() {
        return Prioridad;
    }

    public LocalDateTime getFechaIngreso() {
        return FechaIngreso;
    }

    @Override
    public int compareTo(Tarea otraTarea){

        int resultadoPrioridad = 0;

        resultadoPrioridad = Integer.compare(Prioridad, otraTarea.getPrioridad());

        if (resultadoPrioridad != 0) return resultadoPrioridad;
        else {

            resultadoPrioridad = FechaIngreso.compareTo(otraTarea.getFechaIngreso());

            return resultadoPrioridad;

        }

    }

    @Override
    public String toString(){

        String mensaje = "";

        mensaje = "\n --  " + Titulo + " -- \n* Prioridad: " + Prioridad + "\n* Fecha Ingreso: " + FechaIngreso + "\n";

        return mensaje;
    }

}
