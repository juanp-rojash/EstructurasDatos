package com.example.LinkedListSimple;

public class Node <T> {

    private T Data;
    private Node<T> Referencia;

    public Node(T data){

        Data = data;
        Referencia = null;

    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public Node<T> getReferencia() {
        return Referencia;
    }

    public void setReferencia(Node<T> referencia) {
        Referencia = referencia;
    }

    public String toString(){

        String mensaje = "";

        mensaje = "\t[ " + Data + " | -> ] " + Referencia;

        return mensaje;

    }

}
