package estructuradatosbasicas;

import java.util.UUID;

public class Caja  <T> /*<T, K>*/ {

    private UUID idCaja;
    private T contenido;

    public Caja(T contenido){

        idCaja = UUID.randomUUID();
        this.contenido = contenido;

    }

    public T getContenido(){ return contenido; }

    public void setContenido(T contenido){ this.contenido = contenido; }

    @Override
    public String toString(){

        return "\nCaja [ Contenido: " + contenido + "]\n";

    }

}
