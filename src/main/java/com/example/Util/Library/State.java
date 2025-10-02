package com.example.Util.Library;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class State {

    public static void printState(int [] parent) {

        Node node = null;

        Graph graph = new SingleGraph("Red de Amigos");

        // Estilo CSS para nodos y aristas
        graph.setAttribute("ui.stylesheet",
                "node { fill-color: lightblue; size: 25px; text-size: 15px; } "
                        + "edge { fill-color: gray; size: 2px; }");

        for (int i = 0; i < parent.length; i++) {
            node = graph.addNode(String.valueOf(i));
            node.setAttribute("ui.label", i);
        }

        graph.display();

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] != i) {
                graph.addEdge(i + "-" + parent[i], String.valueOf(i), String.valueOf(parent[i]));
            }
        }

    }

}
