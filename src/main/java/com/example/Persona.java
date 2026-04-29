package com.example;

import java.sql.Timestamp;
import java.util.Objects;

public class Persona {

    private final int id;
    private final String nombre;
    private final Timestamp fechaNacimiento;

    public Persona(int id, String nombre, Timestamp fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + " '}'";
    }

    @Override
    public boolean equals(Object o){

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // La cantidad de atributos que se comparen en el Equals tambien se les debe de aplicar el Hashing

        Persona persona = (Persona) o;
        return id == persona.id &&
                nombre.equals(persona.nombre) &&
                fechaNacimiento.equals(persona.fechaNacimiento);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre, fechaNacimiento);

    }

}
