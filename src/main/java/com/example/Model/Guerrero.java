package com.example.Model;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Guerrero {

    private int id;
    private String name;
    private String ki;
    private String race;
    private String gender;
    private String image;

    public Guerrero(int id, String image, String gender, String race, String ki, String name) {

        log.info("Entrada de datos");

        this.id = id;
        this.image = image;
        this.gender = gender;
        this.race = race;
        this.ki = ki;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKi() {
        return ki;
    }

    public String getRace() {
        return race;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Guerrero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ki='" + ki + '\'' +
                ", race='" + race + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
