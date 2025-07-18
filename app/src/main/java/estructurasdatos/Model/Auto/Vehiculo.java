package estructurasdatos.Model.Auto;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import estructurasdatos.Model.Auto.Interface.Conduccion;
import estructurasdatos.Model.Taller.Reparacion;

public abstract class Vehiculo implements Conduccion{

    private static final Logger logger = LogManager.getLogger(Vehiculo.class.getName());

    protected String placa;
    protected String marca;
    protected String modelo;
    protected ArrayList<Reparacion> reparacion;
    protected ArrayList<Reparacion> historialReparaciones;

    public Vehiculo() {
        this.marca = "";
        this.modelo = "";
        this.reparacion = new ArrayList<>();
        this.historialReparaciones = new ArrayList<>();
    }

    public Vehiculo(String marca, String modelo, String placa) {
        
        try {

            if (placa == null || placa.isEmpty()){
                throw new IllegalArgumentException("Placa no puede ser " + (placa == null ? "nula" : "vacia"));
            }

            if (marca == null || marca.isEmpty()) {
                throw new IllegalArgumentException("Marca no puede ser nula o vacía " + (marca == null ? "nula" : "vacia"));
            }

            if (modelo == null || modelo.isEmpty()) {
                throw new IllegalArgumentException("Modelo no puede ser nulo o vacío " + (modelo == null ? "nula" : "vacia"));
            }

            this.marca = marca;
            this.modelo = modelo;
            this.reparacion = new ArrayList<>();
            this.historialReparaciones = new ArrayList<>();
            this.placa = placa;
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al crear el vehículo: " + e.getMessage());
        }

    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public ArrayList<Reparacion> getReparacion() {
        return reparacion;
    }

    public String getPlaca(){
        return placa;
    }

    public ArrayList<Reparacion> getHistoricoReparaciones(){
        return historialReparaciones;
    }

    public void agregarReparacion(Reparacion reparacion) {

        try {

            logger.info("Agregando reparación al vehículo: " + marca + " " + modelo);

            if (reparacion == null) {
                throw new IllegalArgumentException("La reparación no puede ser nula");
            }

            this.reparacion.add(reparacion);

        } catch (IllegalArgumentException e) {

            logger.error("Error al agregar la reparación: " + e.getMessage(), e);

            throw new IllegalArgumentException("Error al agregar la reparación: " + e.getMessage());
        }

    }

    public boolean traspasoReparacionesHistorico(Vehiculo vehiculo) {

        try {

            logger.info("Transfiriendo reparaciones al histórico del vehículo: " + marca + " - " + modelo + " - " + placa);

            vehiculo.historialReparaciones.addAll(reparacion);
            vehiculo.reparacion.clear();

            return true;

        } catch (IllegalStateException e) {

            logger.error("Error al transferir las reparaciones: " + e.getMessage(), e);

            return false;
        }

    }

}
