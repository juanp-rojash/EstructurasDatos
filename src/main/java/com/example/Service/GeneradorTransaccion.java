package com.example.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.example.Model.Persona;
import com.example.Model.Producto;
import com.example.Model.Transaccion;
import com.example.Util.Evaluador;
import com.example.Util.PerformanceMonitor;

public class GeneradorTransaccion {

    private static final Logger logger = LogManager.getLogger( GeneradorTransaccion.class.getName() );
    private static final Logger loggerTiempos = LogManager.getLogger( "tiempos" );

    public static List<Transaccion> orquestadorProceso(String proceso, long cantidad) throws Exception{

        try {

            List<Transaccion> transacciones = new ArrayList<>();

            logger.info("Iniciando el proceso de generación de transacciones: " + proceso + " | Cantidad: " + cantidad );

            long startTime = System.currentTimeMillis();

            PerformanceMonitor monitor = new PerformanceMonitor("Generación de Transacciones - " + proceso + " - Cantidad: " + cantidad);

            monitor.inicio();

            switch (proceso.trim().toLowerCase()) {

                case "optimo":

                    transacciones = generarTransaccionesOptimo(cantidad);
                    
                    break;

                case "costoso":

                    transacciones = generarTransaccionesCostoso(cantidad);
                    
                    break;
            
                default:

                    transacciones = new ArrayList<>();

                    logger.warn("Proceso no reconocido. No se generaron transacciones.");

                    break;
            }

            monitor.finalizado();

            long endTime = System.currentTimeMillis();

            loggerTiempos.info("Proceso: " + proceso + " | Tiempo de ejecución: " + (endTime - startTime) + " ms" );

            return transacciones;
            
        } 
        catch (Exception e) {

            logger.error("Error en el proceso de generación de transacciones: " + e.getMessage() + e);

            throw new Exception("Error en el proceso de generación de transacciones: " + e.getMessage() + e);

        }

    }

    private static List<Transaccion> generarTransaccionesOptimo(long cantidad) {

        try {

            logger.info("Iniciando generación óptima de transacciones...");

            List<Transaccion> transacciones = new ArrayList<>();

            LinkedList<Producto> productos = null;

            Random random = new Random();

            Persona persona = null;

            String clasificacion = "";

            double monto = 0.0;

            for (int i = 0; i < cantidad; i++) {
                
                persona = new Persona(i, "Persona " + i, (short) (20 + random.nextInt(30)));

                clasificacion = switch (random.nextInt(3)) {
                    case 0 -> "ALTA";
                    case 1 -> "MEDIA";
                    default -> "BAJA";
                };

                monto = random.nextDouble() * 1000;

                productos = new LinkedList<>();

                productos.add(new Producto("Producto " + i, "Categoria " + (i % 5), random.nextDouble() * 100, random.nextInt(10) + 1));

                transacciones.add(new Transaccion(persona, clasificacion, monto, productos));

            }

            logger.info("Generación óptima de transacciones completada.");

            return transacciones;
            
        } 
        catch (Exception e) {
            
            logger.error("Error en la generación óptima de transacciones: " + e.getMessage() + e);

            return new ArrayList<>();

        }

    }

    private static List<Transaccion> generarTransaccionesCostoso(long cantidad) {

        try {

            logger.info("Iniciando generación costosa de transacciones...");

            List<Transaccion> transacciones = new ArrayList<>();

            Random random = new Random();

            for (int i = 0; i < cantidad; i++) {
                
                Persona persona = new Persona(i, "Persona " + i, (short) (20 + random.nextInt(30)));
                String clasificacion = switch (random.nextInt(3)) {
                    case 0 -> "ALTA";
                    case 1 -> "MEDIA";
                    default -> "BAJA";
                };
                double monto = random.nextDouble() * 1000;
                LinkedList<Producto> productos = new LinkedList<>();
                productos.add(new Producto("Producto " + i, "Categoria " + (i % 5), random.nextDouble() * 100, random.nextInt(10) + 1));

                transacciones.add(new Transaccion(persona, clasificacion, monto, productos));
            }

            logger.info("Generación costosa de transacciones completada.");

            return transacciones;
            
        } 
        catch (Exception e) {
            
            logger.error("Error en la generación costosa de transacciones: " + e.getMessage() + e);

            return new ArrayList<>();

        }

    }

    public static void analizarOrdenamiento(List<Transaccion> transacciones) throws Exception {

        try {

            mostrarDatosLista(transacciones);

            PerformanceMonitor monitor = new PerformanceMonitor("Ordenamiento de Transacciones");
            
            monitor.inicio();

            // Ejemplo de ordenamiento// Ordenar las transacciones por clasificación y luego por id
            transacciones.sort(Comparator
                .comparing((Transaccion t) -> t.getClasificacion()) // Ordenar por clasificación
                .thenComparing(t -> ((Persona) t.getPersona()).getId())); // Ordenar por id de la persona


            monitor.finalizado();

            // Evaluar el peso del objeto ordenado
            //String evaluacion = Evaluador.medirPesoObjeto(transacciones);

            //logger.info("\n\nEvaluación del peso del objeto ordenado: " + evaluacion + "\n\n");

            mostrarDatosLista(transacciones);
            
        } 
        catch (Exception e) {
            
            logger.error("Error en el análisis del ordenamiento de transacciones: " + e.getMessage() + e);

            throw new Exception("Error en el análisis del ordenamiento de transacciones: " + e.getMessage() + e);

        }
    }

    private static void mostrarDatosLista(List<Transaccion> transacciones) {

        int size = transacciones.size();
        int limit = Math.min(10, size);

        logger.info("Primeros " + limit + " elementos:");
        transacciones.stream().limit(limit).forEach(t -> logger.info(t.toString()));

        logger.info("Últimos " + limit + " elementos:");
        transacciones.stream().skip(size - limit).forEach(t -> logger.info(t.toString()));

    }

    public static void procesoListaEnlazada(long cantidadDatos) throws Exception {
        PerformanceMonitor monitor = new PerformanceMonitor("Comparación LinkedList");

        // Crear estructuras
        LinkedList<Integer> linkedList = new LinkedList<>();

        // Insertar elementos
        monitor.inicio();
        for (int i = 0; i < cantidadDatos; i++) {
            linkedList.add(i);
        }
        monitor.finalizado();

        // Eliminar elementos
        monitor.inicio();
        while (!linkedList.isEmpty()) {
            linkedList.poll();
        }
        monitor.finalizado();
    }

    public static void procesoArrayDeque(long cantidadDatos) throws Exception {
        PerformanceMonitor monitor = new PerformanceMonitor("Comparación ArrayDeque");

        // Crear estructuras
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        // Insertar elementos
        monitor.inicio();
        for (int i = 0; i < cantidadDatos; i++) {
            arrayDeque.add(i);
        }
        monitor.finalizado();

        // Eliminar elementos
        monitor.inicio();
        while (!arrayDeque.isEmpty()) {
            arrayDeque.poll();
        }
        monitor.finalizado();
    }

}
