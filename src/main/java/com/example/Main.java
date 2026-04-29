package com.example;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.*;

public class Main {

    public static void main(String[] args) {

        Map<String, Integer> mapaSimple = new HashMap<>();

        mapaSimple.put("Enero", 1);
        mapaSimple.put("Febrero", 2);
        mapaSimple.put("Marzo", 3);

        System.out.println(mapaSimple);

        // Consultar un dato
        System.out.println("Mes de Febrero: " + mapaSimple.get("Febrero"));

        System.out.println("Existe en el mapa Abril: " + mapaSimple.containsKey("Abril"));

        for(Entry<String, Integer> mes : mapaSimple.entrySet()){

            System.out.println(mes.getKey() + " " + mes.getValue());

        }

        Map<Persona, String> organigrama = new HashMap<>();

        Persona p1 = new Persona(1, "Juan", Timestamp.valueOf("2020-01-01 00:00:00"));
        Persona p2 = new Persona(2, "Maria", Timestamp.valueOf("2020-02-01 00:00:00"));
        Persona p3 = new Persona(3, "Pedro", Timestamp.valueOf("2020-03-01 00:00:00"));

        organigrama.put(p1, "Gerente");
        organigrama.put(p2, "Coordinador");
        organigrama.put(p3, "Analista");

        System.out.println(organigrama);

        Persona p4 = new Persona(1, "Juan", Timestamp.valueOf("2020-01-01 00:00:00"));

        System.out.println("Persona 1 == Persona 4: " + p1.equals(p4));
        System.out.println("Persona 1 Hash " + p1.hashCode() + " == Persona 4 Hash " + p4.hashCode());

        organigrama.put(p1, "Director");
        System.out.println(organigrama);

        Persona p5 = new Persona(1, "Marcos", Timestamp.valueOf("2020-01-01 00:00:00"));
        String vacante = organigrama.remove(p1);

        organigrama.put(p5, vacante);

        System.out.println(p5);
        System.out.println(organigrama.get(p5));

        p5.getFechaNacimiento().setTime(100000000);

        System.out.println(p5);
        System.out.println(organigrama.get(p5));
    }

}