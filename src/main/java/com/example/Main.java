package com.example;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        // Cola de prioridad Minima: MinHeap
        PriorityQueue<Integer> colaPrioridadMinima = new PriorityQueue<>();

        colaPrioridadMinima.add(10);
        colaPrioridadMinima.add(29);
        colaPrioridadMinima.add(5);
        colaPrioridadMinima.add(12);

        System.out.println("Cola Prioridad Minima");
        System.out.println(colaPrioridadMinima);

        // Cola de prioridad Maxima: MaxHeap
        PriorityQueue<Integer> colaPrioridadMaxima = new PriorityQueue<>(Collections.reverseOrder());

        colaPrioridadMaxima.add(10);
        colaPrioridadMaxima.add(29);
        colaPrioridadMaxima.add(5);
        colaPrioridadMaxima.add(12);

        System.out.println("Cola Prioridad Maxima");
        System.out.println(colaPrioridadMaxima);

        System.out.println("Obtenemos el menor elemento");
        System.out.println(colaPrioridadMinima.remove());
        System.out.println("Cola Prioridad Minima");
        System.out.println(colaPrioridadMinima);

        System.out.println("Obtenemos el mayor elemento");
        System.out.println(colaPrioridadMaxima.remove());
        System.out.println("Cola Prioridad Maxima");
        System.out.println(colaPrioridadMaxima);

        List<Integer> elementosNumericos = Arrays.asList(40, 54, 100, 21, 3, 1, 89);

        PriorityQueue<Integer> colaprioridad = new PriorityQueue<>(elementosNumericos);

        while(!colaprioridad.isEmpty()){

            System.out.println(colaprioridad.poll());

        }

        ////////////

        PriorityQueue<Tarea> prioridadTareas = new PriorityQueue<>(Collections.reverseOrder());

        prioridadTareas.add(new Tarea("Ir al gimnasio", 2, LocalDateTime.now().minusMinutes(2)));
        prioridadTareas.add(new Tarea("Sacar el pase de conduccion", 1, LocalDateTime.now().minusMinutes(2)));
        prioridadTareas.add(new Tarea("Clash Royale", 1, LocalDateTime.now().minusMinutes(10)));
        prioridadTareas.add(new Tarea("Tomar Cocteles", 10, LocalDateTime.now().minusMinutes(5)));

        while(!prioridadTareas.isEmpty()){

            System.out.println(prioridadTareas.poll());

        }

    }

}