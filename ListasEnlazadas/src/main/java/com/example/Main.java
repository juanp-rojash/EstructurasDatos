package com.example;

import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    
    public static void main(String[] args) {

        Peticion<String> p1 = new Peticion<>("Solicitud Inicial");
        Peticion<Integer> p2 = new Peticion<>(1000);
        Peticion<Float> p3 = new Peticion<>(3.1415f);
        Peticion<String> p4 = new Peticion<>("Solicitud Media");
        Peticion<Float> p5 = new Peticion<>(10220.5f);
        Peticion<Byte> p6 = new Peticion<>((byte)2);

        LinkedList<Peticion<?>> listaElementos = new LinkedList<>();

        listaElementos.add(p1);
        listaElementos.add(p2);
        listaElementos.add(p3);

        System.out.println("Lista: \n\n");
        System.out.println(listaElementos);

        listaElementos.add(1, p5);

        System.out.println("Lista: \n\n");
        System.out.println(listaElementos);

        listaElementos.addFirst(p4);
        listaElementos.addLast(p6);

        System.out.println("Lista: \n\n");
        System.out.println(listaElementos);

        Peticion aux;

        aux = listaElementos.removeFirst();

        System.out.println("Lista: \n\n");
        System.out.println(listaElementos);

        aux = listaElementos.pollLast();

        System.out.println("Lista: \n\n");
        System.out.println(listaElementos);

        for(Peticion p : listaElementos){

            System.out.println(p);

        }

        System.out.println("\n");

        for(int i = 0; i < listaElementos.size(); i++){

            System.out.println("Indice: " + i + " Data: " + listaElementos.get(i));

        }

        System.out.println("\n");
        
    }
}