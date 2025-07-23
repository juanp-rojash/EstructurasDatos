package adt.model;

public class Persona {

    private String nombre;
    private int edad;

    public Persona(){
        this.nombre = "";
        this.edad = 0;
    }

    public Persona(String nombre, int edad) throws Exception{

        try {
            
            if (nombre.trim().isEmpty() || nombre == null){
                throw new Exception("Nombre de usuario invalido " + (nombre.trim().isEmpty() ? "Nombre vacio" : "valor nulo"));
            }

            if (edad < 0){
                throw new Exception("Edad invalida");
            }

            this.nombre = nombre;
            this.edad = edad;

        } 
        catch (Exception e) {
            throw new Exception(e.getMessage() + e);
        }

    }

    public int aumentaEdad(int anios) throws Exception{

        try {
            if(anios <= 0){
                return this.edad;
            }
            else{
                this.edad += anios;
                return this.edad;
            }
        } 
        catch (Exception e) {
            throw new Exception("Error al ejecutar el metodo aumentaEdad");
        }

    }

    @Override
    public String toString(){
        String mensaje = "Persona {nombre = " + this.nombre + ", edad = " + this.edad + "}";
        return mensaje;
    }

    @Override
    public boolean equals(Object obj){

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Persona personaAux = (Persona) obj;
        return this.nombre.equals(personaAux.nombre) && this.edad == personaAux.edad;

    }

}
