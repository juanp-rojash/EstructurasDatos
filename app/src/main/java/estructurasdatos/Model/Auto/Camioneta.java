package estructurasdatos.Model.Auto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Camioneta extends Vehiculo {

    private static final Logger logger = LogManager.getLogger(Camioneta.class.getName());

    private String combustible;

    public Camioneta() {
        super();

        logger.info("Creando una nueva camioneta con valores por defecto");

        this.combustible = "";
    }

    public Camioneta(String marca, String modelo, String combustible, String placa) throws Exception {
        super(marca, modelo, placa);

        try {

            logger.info("Camioneta, creando una nueva camioneta con los siguientes valores: " +
                    "marca=" + marca + ", modelo=" + modelo + ", combustible=" + combustible + ", placa=" + placa);

            if(combustible == null || combustible.isEmpty()) {
                throw new IllegalArgumentException("El combustible no puede ser nulo o vacío");
            }

            this.combustible = combustible;

        } catch (Exception e) {

            logger.error("Error al crear la camioneta: " + e.getMessage(), e);

            throw new Exception("Error al crear la camioneta: " + e.getMessage(), e);
        }
        
    }

    public String getCombustible() {
        return combustible;
    }

    @Override
    public String arrancar() {
        return "Camioneta " + marca + " - " + modelo + " arrancada.";
    }

    @Override
    public String apagar() {
        return "Camioneta " + marca + " - " + modelo + " apagada.";
    }



}
