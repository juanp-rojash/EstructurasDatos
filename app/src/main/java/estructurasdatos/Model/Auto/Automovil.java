package estructurasdatos.Model.Auto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import estructurasdatos.Model.Taller.Reparacion;

public class Automovil extends Vehiculo {

    private static final Logger logger = LogManager.getLogger(Reparacion.class.getName());

    private String tipoTransmision;

    public Automovil() {
        super();

        logger.info("Creando un nuevo automóvil con valores por defecto");

        this.tipoTransmision = "";
    }

    public Automovil(String marca, String modelo, String tipoTransmision, String placa) throws Exception {
        super(marca, modelo, placa);

        try {

            logger.info("Automóvil, creando un nuevo automóvil con los siguientes valores: " +
                    "marca=" + marca + ", modelo=" + modelo + ", tipoTransmision=" + tipoTransmision + ", placa=" + placa);

            if (tipoTransmision == null || tipoTransmision.isEmpty()) {
                throw new IllegalArgumentException("El tipo de transmisión no puede ser nulo o vacío");
            }

            this.tipoTransmision = tipoTransmision;

        } catch (Exception e) {
            logger.error("Error al crear el automóvil: " + e.getMessage(), e);

            throw new Exception("Error al crear el automóvil: " + e.getMessage(), e);
        }
        
    }

    public String getTipoTransmision() {
        return tipoTransmision;
    }

    @Override
    public String arrancar() {
        return "Automóvil " + marca + " - " + modelo + " arrancado.";
    }

    @Override
    public String apagar() {
        return "Automóvil " + marca + " - " + modelo + " apagado.";
    }

}
