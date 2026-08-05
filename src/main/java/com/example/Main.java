package com.example;

import com.example.Model.Guerrero;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Main {

    public static void main(String[] args) {

        try {

            List<Guerrero> personajes = new ArrayList<>();
            
            File rutaArchivo = new File("src/main/resources/guerreros.txt");
            FileReader archivoLectura = new FileReader(rutaArchivo);

            try ( BufferedReader datosArchivo = new BufferedReader(archivoLectura) ){

                String [] datosSeparado;
                String linea = datosArchivo.readLine();

                while ( (linea = datosArchivo.readLine()) != null ){

                    datosSeparado = linea.split(";");

                    if (datosSeparado.length < 6) {
                        log.warn("Línea omitida por formato incorrecto: {}", linea);
                        continue;
                    }

                    int id = Integer.parseInt(datosSeparado[0].trim());
                    String name = datosSeparado[1].trim();
                    String ki = datosSeparado[2].trim();
                    String race = datosSeparado[3].trim();
                    String gender = datosSeparado[4].trim();
                    String image = datosSeparado[5].trim();

                    Guerrero g = new Guerrero(id, image, gender, race, ki, name);
                    log.info("Guerrero creado: {}", g);
                    personajes.add(g);
                }

                

            } catch (Exception e) {
                log.error("Error procesando el archivo de guerreros", e);
                throw e;
            }

            log.info("Total de guerreros cargados: {}", personajes.size());

        } catch (IOException io) {
            log.error("Error en la lectura del recurso: {}", io.getMessage(), io);
        } catch (Exception e) {
            log.error("Error en el sistema: {}", e.getMessage(), e);
        }
    }
}
