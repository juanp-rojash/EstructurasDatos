package estructurasdatos.Model.Service;

import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;

import estructurasdatos.Model.Auto.Vehiculo;
import estructurasdatos.Model.Taller.Reparacion;

public class FacturacionService {

    private static final Logger logger = LogManager.getLogger(FacturacionService.class.getName());

    private static final String RUTA_ARCHIVO_FACTURA = "logs/";

    public static boolean generarFactura(Vehiculo vehiculo) throws Exception {

        try {
            
            logger.info("Generando factura al vehiculo: " + vehiculo.getPlaca() + " Cantidad de Reparaciones: " + vehiculo.getReparacion().size());

            int costoTotalReparaciones = 0;
            boolean operacionTraspaso = false;
            String mensajeReparaciones = "";
            ArrayList<Reparacion> reparaciones = (ArrayList<Reparacion>) vehiculo.getReparacion().clone();

            operacionTraspaso = vehiculo.traspasoReparacionesHistorico(vehiculo);

            if(!operacionTraspaso) {
                logger.error("Error al traspasar las reparaciones al historial del vehiculo: " + vehiculo.getPlaca());
                return false;
            }
            else{
                logger.info("Reparaciones traspasadas al historial del vehiculo: " + vehiculo.getPlaca());
            }

            costoTotalReparaciones = reparaciones.stream().mapToInt(Reparacion::getCostos).sum();

            mensajeReparaciones = reparaciones.stream()
                .map(r -> "\n - Reparación: " + r.getDescripcion() + "\n - Costo: " + r.getCostos() + "\n - Fecha: " + r.getFechaImplemntacion() + "\n - Repuesto: " + r.getRepuesto())
                .reduce("", (a, b) -> a + "\n\n ====== | ====== \n\n" + b);

            logger.info("Factura generada para el vehiculo: " + vehiculo.getPlaca() + " con un costo total de: " + costoTotalReparaciones);

            generarArchivo(vehiculo, mensajeReparaciones, costoTotalReparaciones);

            logger.info("Factura generada exitosamente para el vehiculo: " + vehiculo.getPlaca());

            return true;
            
        } 
        catch (Exception e) {
            throw new Exception("Error al generar la factura del vehiculo: " + vehiculo.getPlaca() + "\n " + e.getMessage() + e);
        }

    }

    private static void generarArchivo(Vehiculo vehiculo, String mensajeReparaciones, int costoTotalReparaciones) throws Exception {
        
        try {
            
            logger.info("Generando archivo de factura para el vehiculo: " + vehiculo.getPlaca());

            Date fechaActual = new Date(System.currentTimeMillis());

            FileWriter fileWriter = new FileWriter(RUTA_ARCHIVO_FACTURA + "factura_" + vehiculo.getPlaca() + "_" + fechaActual + ".txt");

            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(mensajeReparaciones + "\n\nTotal: " + costoTotalReparaciones);
            } catch (Exception e) {
                logger.error("Error al escribir en el archivo de factura: " + e.getMessage(), e);
                throw new Exception("Error al escribir en el archivo de factura: " + e.getMessage(), e);
            }

            logger.info("Archivo de factura generado exitosamente para el vehiculo: " + vehiculo.getPlaca());

        } 
        catch (Exception e) {
            throw new Exception("Error al generar el archivo de la factura del vehiculo: " + vehiculo.getPlaca() + "\n " + e.getMessage() + e);
        }

    }

}
