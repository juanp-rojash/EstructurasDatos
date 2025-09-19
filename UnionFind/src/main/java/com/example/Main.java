package com.example;


import com.example.Util.WeightedQuickUnionUF;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            int nodoA = 0;

            int nodoB = 0;

            int cantidadNodos = 0;

            WeightedQuickUnionUF unionFind = null;

            Scanner sc = new Scanner(System.in);

            //

            System.out.print("Ingrese la cantidad de personas: ");
            cantidadNodos = Integer.parseInt(sc.nextLine());

            unionFind = new WeightedQuickUnionUF(cantidadNodos);

            System.out.println("Ingrese las uniones que desea (Ej: 0 0");

            while(true){

                nodoA = sc.nextInt();
                nodoB = sc.nextInt();

                if (nodoA == -1 && nodoB == -1) break;

                if (!unionFind.connected(nodoA, nodoB)){
                    unionFind.union(nodoA, nodoB);
                }
                else{
                    System.out.println(nodoA + " <-> " + nodoB + " = Ya estan conectados");
                }

            }

            System.out.println(unionFind.imprimirArbol());


        }
        catch (Exception e){

            System.out.println("Error:" + e);

        }

    }

}