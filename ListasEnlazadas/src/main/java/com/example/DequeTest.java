package com.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DequeTest {

    public static void main(String [] args){

        Peticion<String> p1 = new Peticion<>("Solicitud Inicial");
        Peticion<Integer> p2 = new Peticion<>(1000);
        Peticion<Float> p3 = new Peticion<>(3.1415f);
        Peticion<String> p4 = new Peticion<>("Solicitud Media");
        Peticion<Float> p5 = new Peticion<>(10220.5f);
        Peticion<Byte> p6 = new Peticion<>((byte)2);

        // Interfaz = new Implementacion
        Deque<Peticion> deque = new ArrayDeque<>();

        Queue<String> q = new LinkedList<>();
        Queue<String> q2 = new ArrayDeque<>();

        deque.add(p1);
        deque.addFirst(p2);
        deque.addLast(p3);

        System.out.println("\n\n");
        System.out.println(deque);

        deque.remove();

        for (Peticion p : deque){

            System.out.println(p);

        }

    }

}
