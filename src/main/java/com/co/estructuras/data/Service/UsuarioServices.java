package com.co.estructuras.data.Service;

import com.co.estructuras.data.Model.Dto.user.UsuarioRequest;
import com.co.estructuras.data.Model.Dto.user.UsuarioResponse;
import com.co.estructuras.data.Model.Entity.Usuario;
import com.co.estructuras.data.Model.Entity.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse crearUsuario(UsuarioRequest usuario){

        try{

            Usuario cliente = Usuario.builder()
                    .nombre(usuario.nombre())
                    .apellido(usuario.apellido())
                    .email(usuario.email())
                    .password(usuario.password())
                    .edad(usuario.edad())
                    .build();

            Usuario guardado = usuarioRepository.save(cliente);

            return new UsuarioResponse(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail());

        }
        catch(Exception e){

            return null;

        }

    }

    public List<UsuarioResponse> listarUsuarios(){

        try{

            return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getEmail()
                ))
                .toList();
                
        }
        catch(Exception e){

            return null;

        }

    }

}
