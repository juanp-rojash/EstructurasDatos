package com.co.estructuras.data.Controller;


import com.co.estructuras.data.Model.Dto.user.UsuarioRequest;
import com.co.estructuras.data.Model.Dto.user.UsuarioResponse;
import com.co.estructuras.data.Service.UsuarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuario")
@Tag(
        name = "Gestión de Usuarios",
        description = "Endpoints para crear, obtener y gestionar usuarios del sistema"
)
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioService) {
        this.usuarioServices = usuarioService;
    }

    @PostMapping("/crear")
    @Operation(
            summary = "Crear nuevo usuario",
            description = "Crea un nuevo usuario en el sistema con los datos proporcionados. El email debe ser único y válido."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos - Validación fallida"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    public UsuarioResponse crearUsuario(@Valid @RequestBody UsuarioRequest usuario){

        return usuarioServices.crearUsuario(usuario);

    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Recupera la lista de todos los usuarios registrados en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de usuarios obtenido exitosamente"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    public List<UsuarioResponse> obtenerUsuarios(){

        return usuarioServices.listarUsuarios();

    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener usuario por ID",
            description = "Recupera los detalles de un usuario específico usando su identificador único"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    public String obtenerUsuario(
            @Parameter(
                    name = "id",
                    description = "Identificador único del usuario",
                    required = true,
                    example = "1"
            )
            @PathVariable long id){

        return "Usuario con id: " + id;

    }

}
