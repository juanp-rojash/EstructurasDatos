package com.example;

import com.example.Model.Persona;

import com.example.Model.Transaccion;
import com.example.Service.GeneradorTransaccion;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            long cantidadTransacciones = 10000000;

            String proceso = "optimo"; // Proceso a ejecutar: "optimo" o "costoso"

            List<Transaccion> transacciones = GeneradorTransaccion.orquestadorProceso(proceso, cantidadTransacciones);

            GeneradorTransaccion.analizarOrdenamiento(transacciones);

            GeneradorTransaccion.procesoListaEnlazada(cantidadTransacciones);

            GeneradorTransaccion.procesoArrayDeque(cantidadTransacciones);


        } catch (Exception e) {
            System.out.println("Error en el sistema: " + e.getMessage() + e);
        }

    }
}