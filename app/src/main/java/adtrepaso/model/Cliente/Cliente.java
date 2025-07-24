package adtrepaso.model.Cliente;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cliente {

    private static final Logger logger = LogManager.getLogger(Cliente.class.getName());

    private String nombre;
    private String apellido;
    private String email;
    private String identificacion;

    public Cliente(String nombre, String apellido, String email, String identificacion){

        try {
            
            if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()
            || email == null || email.isEmpty() || identificacion == null || identificacion.isEmpty()){

                logger.error("Datos vacios / nulos para crear un Cliente");

                throw new IllegalArgumentException("Los campos no pueden ser vacios o nulos");

            }

            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
            this.identificacion = identificacion;

        } 
        catch (Exception e) {
            
            logger.error("Error al instanciar Cliente: " + e);

            throw e;

        }

    }

    public String getNombre(){ return this.nombre; }
    public String getApellido(){ return this.apellido; }
    public String getEmail(){ return this.email; }
    public String getIdentificacion(){ return this.identificacion; }

    @Override
    public String toString(){
        return "Cliente: " + nombre + " " + apellido + "\nIdentificacion: " + identificacion + "\nEmail: " + email;
    }

    @Override
    public boolean equals(Object obj){

        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;

        Cliente c = (Cliente) obj;

        /* TODO: Comparacion de atributo por atributo*/

        return true;

    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre, apellido, email, identificacion);
    }

}
