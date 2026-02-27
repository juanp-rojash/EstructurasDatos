package com.example.LinkedListSimple;

public class LinkedSimpleList <T> {

    private Node<T> Cabeza;
    private int Size;

    public LinkedSimpleList(){

        Cabeza = null;
        Size = 0;

    }

    public void add(T data){

        Node<T> nodo = new Node(data);

        if ( Cabeza == null ){
            Cabeza = nodo;
        }
        else{
            nodo.setReferencia(Cabeza);
            Cabeza = nodo;
        }

        Size++;

    }

    public void remove(T data){

        if ( Cabeza == null ) return;

        if ( Cabeza.getData().equals(data) ){

            Cabeza = Cabeza.getReferencia();
            Size--;

            return;

        }

        Node<T> nodo = Cabeza;

        while( nodo.getReferencia() != null ){

            if ( nodo.getReferencia().getData().equals(data) ){

                nodo.setReferencia( nodo.getReferencia().getReferencia() );
                Size--;

                return;

            }

            nodo = nodo.getReferencia();

        }

    }

    public String toString(){

        String mensaje = "";

        Node<T> nodo = Cabeza;

        while ( nodo != null ){

            mensaje += nodo.toString();

            nodo = nodo.getReferencia();

        }

        return mensaje;

    }

}
