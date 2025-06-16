package es.unican.nelson.polaflix_nelson.controladorRest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.nelson.polaflix_nelson.dominio.*;
import es.unican.nelson.polaflix_nelson.repositories.CapituloRepository;
import es.unican.nelson.polaflix_nelson.repositories.FacturaRepository;
import es.unican.nelson.polaflix_nelson.repositories.SerieRepository;
import es.unican.nelson.polaflix_nelson.repositories.UsuarioRepository;
import es.unican.nelson.polaflix_nelson.repositories.CapituloIDRepository;
import jakarta.transaction.Transactional;

@RestController
public class UsuariosController {

    //creo un objeto del repositorio de usuarios
    @Autowired
    UsuarioRepository user;

    //creo el repositoario de serie
    @Autowired
    SerieRepository serie;

    //creo el repo de capitulo
    @Autowired
    CapituloRepository capitulo;

    @Autowired
    CapituloIDRepository capitulo_id;

     //devuelvo un usuario
    @GetMapping(value="usuarios", params="nombreUsuario")
    @JsonView({Views.VistaUsuario.class})
    @Transactional
    public ResponseEntity<Usuario> getUsuario(@RequestParam("nombreUsuario") String nombre){
        
        Optional<Usuario> usuario = user.getUsuarioByNombreUsuario(nombre);

        ResponseEntity<Usuario> respuesta;

        if(usuario.isPresent()){
            respuesta = ResponseEntity.ok(usuario.get());
        } else {
            respuesta = ResponseEntity.notFound().build();
        }

        return respuesta;
    }

    //anhadir una serie a pendientes
    @PutMapping("usuarios/{id-usuario}/pendientes/{id-serie}")
    @Transactional //abre una transaccion para la BD
    public ResponseEntity<Map<String, String>> anhadirSerie(@PathVariable("id-usuario") Long idUsuario, @PathVariable("id-serie") long idSerie){
        //ahora recupero primero un usuario
        Optional<Usuario> u = user.getUsuarioById(idUsuario);
        Optional<Serie> s = serie.getSerieById(idSerie);
        Map <String, String> mensaje;

        //gestiono los errores
        
        ResponseEntity<Map<String,String>> respuesta;
        if(u.isPresent()){
            //el .get me devuelve la serie de dentro
            if(s.isPresent()){
                u.get().getPendientes().add(s.get());
                user.save(u.get());
                mensaje = Map.of("Mensaje", "Operación realizada correctamente");
                respuesta = ResponseEntity.ok(mensaje);
            }else {
                mensaje = Map.of("Mensaje", "Error al encontrar la serie");
            respuesta = new ResponseEntity<Map<String, String>> (mensaje, HttpStatus.NOT_FOUND);
            
            }
            
        } else {
             mensaje = Map.of("Mensaje", "Error al encontrar el usuario");
            respuesta = new ResponseEntity<Map<String, String>> (mensaje, HttpStatus.NOT_FOUND);
            
        }

        return respuesta;
        
    }
    
    //ver un capitulo
    @PutMapping("usuarios/{id-usuario}/verCapitulo/{id-capitulo}")
    @Transactional //abre una transaccion para la BD
    public ResponseEntity<Map<String, String>> verCapitulo(@PathVariable("id-usuario") Long idUsuario, @PathVariable("id-capitulo") Long idCapitulo){
        //ahora recupero primero un usuario
        Optional<Usuario> u = user.getUsuarioById(idUsuario);
        Optional<Capitulo> c = capitulo.getCapituloById(idCapitulo);
        Optional<CapituloID> cid = capitulo_id.getCapituloIDById(idCapitulo);
        Map <String, String> mensaje;

        //gestiono los errores
        
        ResponseEntity<Map<String,String>> respuesta;
        if(u.isPresent()){
            //el .get me devuelve la serie de dentro
            if(c.isPresent() && cid.isPresent()){
                u.get().verCapitulo(c.get(), cid.get());
                user.save(u.get());
                mensaje = Map.of("Mensaje", "Operación realizada correctamente");
                respuesta = ResponseEntity.ok(mensaje);
            }else {
                mensaje = Map.of("Mensaje", "Error al encontrar el capitulo");
            respuesta = new ResponseEntity<Map<String, String>> (mensaje, HttpStatus.NOT_FOUND);
            
            }
            
        } else {
             mensaje = Map.of("Mensaje", "Error al encontrar el usuario");
            respuesta = new ResponseEntity<Map<String, String>> (mensaje, HttpStatus.NOT_FOUND);
            
        }

        return respuesta;
        
    }

     //recuperar las facturas del usuario
    @GetMapping("usuarios/{id-usuario}/facturas")
    @JsonView({Views.VistaFactura.class})
    @Transactional //abre una transaccion para la BD
    public ResponseEntity <?> getFacturas(@PathVariable("id-usuario") Long idUsuario){
        //ahora recupero primero un usuario
        Optional<Usuario> u = user.getUsuarioById(idUsuario);
        
        //gestiono los errores
        
        ResponseEntity<?> respuesta;
         Map <String, String> mensaje;

        if(u.isPresent()){
            //el .get me devuelve la serie de dentro
            if(u.get().getFacturas() != null){
                

                respuesta = ResponseEntity.ok(u.get().getFacturas());
            }else {
                mensaje = Map.of("Mensaje", "No hay facturas");
            respuesta = new ResponseEntity<Map<String, String>> (mensaje, HttpStatus.NOT_FOUND);
            
            }
            
        } else {
             mensaje = Map.of("Mensaje", "Error al encontrar el usuario");
            respuesta = new ResponseEntity<Map<String, String>> (mensaje, HttpStatus.NOT_FOUND);
            
        }

        return respuesta;
        
    }
    
}
