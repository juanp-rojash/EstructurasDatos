package com.co.estructuras.data.Controller;


import com.co.estructuras.data.Model.Dto.user.UsuarioRequest;
import com.co.estructuras.data.Model.Dto.user.UsuarioResponse;
import com.co.estructuras.data.Service.UsuarioServices;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioService) {
        this.usuarioServices = usuarioService;
    }

    @PostMapping("/crear")
    public UsuarioResponse crearUsuario(@Valid @RequestBody UsuarioRequest usuario){

        return usuarioServices.crearUsuario(usuario);

    }

    @GetMapping
    public String obtenerUsuarios(){

        return "Usuarios";

    }

    @GetMapping("/{id}")
    public String obtenerUsuario(@PathVariable long id){

        return "Usuario con id: " + id;

    }

}
