package es.unican.nelson.polaflix_nelson.controladorRest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.unican.nelson.polaflix_nelson.dominio.Factura;
import es.unican.nelson.polaflix_nelson.dominio.FacturaRepository;
import es.unican.nelson.polaflix_nelson.dominio.Serie;
import es.unican.nelson.polaflix_nelson.dominio.Usuario;
import es.unican.nelson.polaflix_nelson.dominio.UsuarioRepository;

@RestController
public class UsuariosController {

    //creo un objeto del repositorio de usuarios
    @Autowired
    UsuarioRepository user;

     //devuelvo todas las usuarios
    @GetMapping("usuarios")
    //hoy devuelvo una lista de usuarios
    public ResponseEntity<List<Usuario>> getTodas() {
        
        List<Usuario> usuarios = user.findAll();
        ResponseEntity<List<Usuario>> respuesta;

        if(!usuarios.isEmpty()){
            //hoy no pongo el get porque ya tengo la lista
            respuesta = ResponseEntity.ok(usuarios);
        } else {
            respuesta = ResponseEntity.notFound().build();
        }

        return respuesta;
    }

    //devuelvo todos los campos de un usuario con ese id
    @GetMapping("usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long idUsuario) {
        //ahora recupero primero un usuario
        Optional<Usuario> u = user.getUsuarioById(idUsuario);
        ResponseEntity<Usuario> respuesta;
        if(u.isPresent()){
            //el .get me devuelve la serie de dentro
            respuesta = ResponseEntity.ok(u.get());
        } else {
            respuesta = ResponseEntity.notFound().build();
        }

        return respuesta;
        
    }
    
}
