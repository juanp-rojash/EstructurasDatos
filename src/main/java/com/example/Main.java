package com.example;

import com.example.DTO.PedidoDTO;
import com.example.Model.Pedido;
import com.example.Service.PedidoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Clase principal y punto de entrada de la aplicación.
 *
 * <p>Su propósito es demostrar el ciclo completo de trabajo con JSON usando la
 * librería Jackson y el patrón DTO ↔ Modelo:</p>
 *
 * <ol>
 *   <li><b>Lectura (deserialización):</b> lee archivos JSON del classpath y los
 *       convierte en objetos {@link PedidoDTO}.</li>
 *   <li><b>Mapeo:</b> transforma los DTO en modelos de dominio {@link Pedido}.</li>
 *   <li><b>Escritura (serialización):</b> convierte un modelo de vuelta a DTO y lo
 *       exporta como texto JSON y como archivo físico.</li>
 * </ol>
 */
public class Main {

    /**
     * Método de arranque de la aplicación. Ejecuta, en orden, la lectura de los
     * JSON de ejemplo, su conversión a modelos de dominio, la impresión de los
     * resultados en consola y, finalmente, la exportación de un pedido de vuelta a JSON.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {

        // ObjectMapper es el componente central de Jackson: se encarga de convertir
        // entre JSON (texto) y objetos Java (deserializar) y viceversa (serializar).
        ObjectMapper mapper = new ObjectMapper();

        try{

            // Se cargan los archivos JSON como flujos de entrada desde el classpath
            // (carpeta resources). Uno contiene una lista de pedidos y el otro uno solo.
            InputStream inputStreamCompleto = Main.class.getResourceAsStream("/Dummy/pedidos.json");
            InputStream inputStreamSimple = Main.class.getResourceAsStream("/Dummy/pedido.json");

            // Deserialización de la LISTA: TypeReference le indica a Jackson el tipo
            // genérico exacto (List<PedidoDTO>), información que de otro modo se perdería
            // por el borrado de tipos (type erasure) de Java.
            List<PedidoDTO> listaDtos = mapper.readValue( inputStreamCompleto, new TypeReference<List<PedidoDTO>>() {});

            // Se transforma cada DTO en su modelo de dominio usando un stream y una
            // referencia a método (PedidoDTO::DTO_Modelo).
            List<Pedido> listaModelos = listaDtos.stream().map(PedidoDTO::DTO_Modelo).toList();

            // Deserialización de un OBJETO simple: aquí basta con indicar la clase destino.
            PedidoDTO pedidoDto = mapper.readValue(inputStreamSimple, PedidoDTO.class);
            Pedido pedido = pedidoDto.DTO_Modelo();

            System.out.println("Se cargaron " + listaModelos.size() + " pedidos.");

            // Imprime cada pedido de la lista (invoca su toString()).
            listaModelos.forEach(System.out::println);

            System.out.println(pedido);

            System.out.println("---");

            // Se delega el análisis de los pedidos a la capa de servicio: el Main solo
            // orquesta, mientras que la lógica de negocio vive en PedidoService.
            PedidoService pedidoService = new PedidoService(listaModelos);

            System.out.println("Total de pedidos: " + pedidoService.contarPedidos());
            System.out.printf("Suma total de los pedidos: %.2f%n", pedidoService.totalizarPedidos());

            String identificacionBuscada = "1001234567";
            List<Pedido> pedidos = pedidoService.buscarTodosPorIdentificacionUsuario(identificacionBuscada);
            System.out.println("Pedidos encontrados para " + identificacionBuscada + ": " + pedidos);

            System.out.println("---");

            // Proceso inverso: se toma el primer modelo de dominio y se convierte a DTO
            // para poder serializarlo.
            PedidoDTO pedidoDTOJson = PedidoDTO.Modelo_DTO(listaModelos.get(0));

            // --- OPCIÓN A: Obtener el JSON como texto (String) ---
            // Usamos writerWithDefaultPrettyPrinter() para que el JSON salga indentado y legible
            String jsonTexto = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pedidoDTOJson);

            System.out.println("JSON Generado:");
            System.out.println(jsonTexto);

            // --- OPCIÓN B: Guardarlo directamente en un archivo físico ---
            // Esto creará un archivo 'pedido_exportado.json' en la raíz de tu proyecto
            File archivoSalida = new File("pedido_exportado.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivoSalida, pedidoDTOJson);


        }
        catch (Exception e) {
            // Captura cualquier fallo del proceso (por ejemplo, archivo no encontrado
            // o JSON con formato inválido) y muestra el error en consola.
            System.out.println("Error del sistema: " + e);
        }


    }

}