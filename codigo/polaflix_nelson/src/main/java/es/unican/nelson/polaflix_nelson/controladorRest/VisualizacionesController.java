package es.unican.nelson.polaflix_nelson.controladorRest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.unican.nelson.polaflix_nelson.dominio.Serie;
import es.unican.nelson.polaflix_nelson.dominio.SerieRepository;
import es.unican.nelson.polaflix_nelson.dominio.Visualizacion;
import es.unican.nelson.polaflix_nelson.dominio.VisualizacionRepository;

@RestController
public class VisualizacionesController {
    
    //creo un objeto del repositorio de visualizaciones
    @Autowired
    VisualizacionRepository visualizacion;

    //devuelvo todos los campos de una visualizacion con ese id
    @GetMapping("visualizaciones/{id}")
    public ResponseEntity<Visualizacion> getVisualizacion(@PathVariable("id") Long idVisualizacion) {
        //ahora recupero primero una visualizacion
        Optional<Visualizacion> v = visualizacion.getVisualizacionById(idVisualizacion);
        ResponseEntity<Visualizacion> respuesta;
        if(v.isPresent()){
            //el .get me devuelve la serie de dentro
            respuesta = ResponseEntity.ok(v.get());
        } else {
            respuesta = ResponseEntity.notFound().build();
        }

        return respuesta;
        
    }
}
