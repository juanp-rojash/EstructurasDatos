package estructurasdatos.Model.Taller;

import java.sql.Date;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Reparacion {

    private static final Logger logger = LogManager.getLogger(Reparacion.class.getName());

    private String descripcion;
    private int costos;
    private Date fechaImplemntacion;
    private String repuesto;

    public Reparacion() {

        logger.info("Creando una nueva reparacion con valores por defecto");

        this.descripcion = "";
        this.costos = 0;
        this.fechaImplemntacion = new Date(System.currentTimeMillis());
        this.repuesto = "";
    }

    public Reparacion(String descripcion, int costos, Date fechaImplemntacion, String repuesto) throws Exception {

        logger.info("Reparacion, creando una nueva reparacion con los siguientes valores: " +
                "descripcion=" + descripcion + ", costos=" + costos + ", fechaImplemntacion=" + fechaImplemntacion + ", repuesto=" + repuesto);

        try {

            if (descripcion == null || descripcion.isEmpty()) {
                throw new IllegalArgumentException("La descripcion no puede ser nula o vacia");
            }

            if (costos < 0) {
                throw new IllegalArgumentException("Los costos no pueden ser negativos");
            }

            if (fechaImplemntacion == null) {
                throw new IllegalArgumentException("La fecha de implementacion no puede ser nula");
            }

            if (repuesto == null || repuesto.isEmpty()) {
                throw new IllegalArgumentException("El repuesto no puede ser nulo o vacio");
            }

            this.descripcion = descripcion;
            this.costos = costos;
            this.fechaImplemntacion = fechaImplemntacion;
            this.repuesto = repuesto;
            
        }
        catch (Exception e) {

            logger.error("Error al crear la reparacion: " + e.getMessage(), e);

            throw new Exception("Error al crear la reparacion: " + e.getMessage(), e);
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCostos() {
        return costos;
    }

    public Date getFechaImplemntacion() {
        return fechaImplemntacion;
    }

    public String getRepuesto() {
        return repuesto;
    }

}
