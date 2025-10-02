package com.example;

import com.example.Util.Factory.UnionFindFactory;
import com.example.Util.Factory.UnionFindFactory.UnionFindType;
import com.example.Util.Interface.UnionFind;

import java.util.Scanner;

public class Main {
    private static UnionFind unionFind;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int type = 0;
        int size = 0;

        int nodoP = 0;
        int nodoQ = 0;

        boolean running = true;

        while (running) {

            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Crear nuevo árbol Union-Find");
            System.out.println("2. Realizar unión entre nodos");
            System.out.println("3. Verificar conexión entre nodos");
            System.out.println("4. Mostrar cantidad de componentes");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    System.out.println("\n=== Crear Nuevo Union-Find ===");
                    System.out.println("1. Quick Find");
                    System.out.println("2. Quick Union");
                    System.out.println("3. Weighted Quick Union");
                    System.out.print("Seleccione el tipo de implementación: ");
                    type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el tamaño del árbol: ");
                    size = scanner.nextInt();
                    scanner.nextLine();

                    createNewUnionFind(type, size);

                    break;

                case 2:

                    if (validateUnionFind()) {

                        System.out.print("Ingrese el primer nodo: ");
                        nodoP = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Ingrese el segundo nodo: ");
                        nodoQ = scanner.nextInt();
                        scanner.nextLine();

                        performUnion(nodoP, nodoQ);

                    }

                    break;
                case 3:

                    if (validateUnionFind()){

                        System.out.print("Ingrese el primer nodo: ");
                        nodoP = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Ingrese el segundo nodo: ");
                        nodoQ = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println(checkConnection(nodoP, nodoQ));

                    }

                    break;

                case 4:

                    if (validateUnionFind()) System.out.println(showComponents());

                    break;

                case 5:

                    running = false;

                    break;

                default:

                    System.out.println("Opción no válida");
            }

        }

        scanner.close();

    }

    private static void createNewUnionFind(int type, int size) {

        UnionFindType unionFindType;

        switch (type) {
            case 1:
                unionFindType = UnionFindType.QUICK_FIND;
                break;
            case 2:
                unionFindType = UnionFindType.QUICK_UNION;
                break;
            case 3:
                unionFindType = UnionFindType.WEIGHTED_QUICK_UNION;
                break;
            default:
                System.out.println("Tipo no válido. Usando Quick Find por defecto.");
                unionFindType = UnionFindType.QUICK_FIND;
        }

        unionFind = UnionFindFactory.createUnionFind(unionFindType, size);
    }

    private static void performUnion(int p, int q) {

        try {

            unionFind.union(p, q);
            
        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        }
    }

    private static String checkConnection(int p, int q) {
        
        try {

            boolean connected = unionFind.connected(p, q);

            return "Los nodos " + p + " y " + q + (connected ? " están conectados" : " no están conectados");

        } catch (IllegalArgumentException e) {

            return "Error: " + e.getMessage();

        }
    }

    private static String showComponents() {

        return "Número actual de componentes: \n\n" + unionFind;

    }

    private static boolean validateUnionFind() {
        if (unionFind == null) {
            System.out.println("Error: Primero debe crear un árbol Union-Find");
            return false;
        }
        return true;
    }
}