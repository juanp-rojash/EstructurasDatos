package estructurasdatos.Model.Taller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import estructurasdatos.Model.Auto.Vehiculo;

public class Taller {

    private static final Logger logger = LogManager.getLogger(Taller.class.getName());

    private String sede;
    private ArrayList<Vehiculo> vehiculos;
    private int capacidadMaximaCeldas = 10;

    public Taller() {

        logger.info("Creando un nuevo taller con valores por defecto");

        this.sede = "";
        this.vehiculos = new ArrayList<>();

    }

    public Taller(String sede) throws Exception {

        try {
            
            logger.info("Creando un nuevo taller en la sede: " + sede);

            if (sede == null || sede.isEmpty()) {
                throw new IllegalArgumentException("La sede no puede ser nula o vacía");
            }

            this.sede = sede;
            this.vehiculos = new ArrayList<>();

        } 
        catch (Exception e) {
            
            logger.error("Error al crear el taller: " + e.getMessage(), e);

            throw new Exception("Error al crear el taller: " + e.getMessage(), e);

        }

    }

    public String getSede() {
        return sede;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public int getCapacidadMaximaCeldas() {
        return capacidadMaximaCeldas;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {

        try {

            logger.info("Agregando vehículo a la sede: " + sede);

            if (vehiculo == null) {
                throw new IllegalArgumentException("El vehículo no puede ser nulo");
            }

            if (vehiculos.size() >= capacidadMaximaCeldas) {
                throw new IllegalStateException("Capacidad máxima de celdas alcanzada");
            }

            vehiculos.add(vehiculo);

        } catch (IllegalArgumentException | IllegalStateException e) {

            logger.error("Error al agregar el vehículo: " + e.getMessage(), e);

            throw e;

        }

    }

}
