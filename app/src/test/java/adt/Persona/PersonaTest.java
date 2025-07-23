package adt.Persona;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import adt.model.Persona;

class PersonaTest {

    @Test
    void testAumentaEdadCorrecto() throws Exception {
        Persona persona = new Persona("Juan", 25);
        int nuevaEdad = persona.aumentaEdad(5);
        assertEquals(30, nuevaEdad);
    }

    @Test
    void testAumentaEdadCero() throws Exception {
        Persona persona = new Persona("Maria", 30);
        int nuevaEdad = persona.aumentaEdad(0);
        assertEquals(30, nuevaEdad);
    }

    @Test
    void testAumentaEdadNegativo() throws Exception {
        Persona persona = new Persona("Carlos", 40);
        int nuevaEdad = persona.aumentaEdad(-5);
        assertEquals(40, nuevaEdad);
    }

    /*@Test No se puede lanzar una excepcion en este caso
    1. El método `aumentaEdad` no lanza una excepción cuando se le pasa un valor negativo.
    2. El método simplemente no cambia la edad si el valor es negativo, por lo que no se puede probar una excepción en este caso.
    void testAumentaEdadExcepcion() throws Exception {
        Persona persona = new Persona("Ana", 20);
        Exception exception = assertThrows(Exception.class, () -> {
            persona.aumentaEdad(-1);
        });
        assertTrue(exception.getMessage().contains("Error al ejecutar el metodo aumentaEdad"));
    }*/

}
