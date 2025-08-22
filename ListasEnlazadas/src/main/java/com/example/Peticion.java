package com.example;

import java.util.UUID;

public class Peticion < T > {

    private UUID id;
    public T data;

    public Peticion( T data ){

        this.id = UUID.randomUUID();
        this.data = data;

    }

    @Override
    public String toString(){

        return "Peticion { " + this.data + " } ";

    }

}
