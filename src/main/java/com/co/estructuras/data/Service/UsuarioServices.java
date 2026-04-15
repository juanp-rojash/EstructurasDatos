package com.co.estructuras.data.Service;

import com.co.estructuras.data.Model.Dto.user.UsuarioRequest;
import com.co.estructuras.data.Model.Dto.user.UsuarioResponse;
import com.co.estructuras.data.Model.Entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {

    private static long ID = 0;

    public UsuarioResponse crearUsuario(UsuarioRequest usuario){

        try{

            Usuario cliente = Usuario.builder()
                    .id(ID++)
                    .nombre(usuario.nombre())
                    .apellido(usuario.apellido())
                    .email(usuario.email())
                    .password(usuario.password())
                    .edad(usuario.edad())
                    .build();

            return new UsuarioResponse(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEmail());

        }
        catch(Exception e){

            return null;

        }

    }

}
