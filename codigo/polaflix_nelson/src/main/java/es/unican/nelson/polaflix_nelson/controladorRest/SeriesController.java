package es.unican.nelson.polaflix_nelson.controladorRest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.nelson.polaflix_nelson.dominio.Serie;
import es.unican.nelson.polaflix_nelson.repositories.SerieRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class SeriesController {

    //creo un objeto del repositorio de series
    @Autowired
    SerieRepository sr;

    //devuelvo todos los campos de una serie con ese id
    @GetMapping("series/{id}")
    @JsonView({Views.VistaSerie.class})
    public ResponseEntity<Serie> getSerie(@PathVariable("id") Long idSerie) {
        //ahora recupero primero una serie
        Optional<Serie> s = sr.getSerieById(idSerie);
        ResponseEntity<Serie> respuesta;
        if(s.isPresent()){
            //el .get me devuelve la serie de dentro
            respuesta = ResponseEntity.ok(s.get());
        } else {
            respuesta = ResponseEntity.notFound().build();
        }

        return respuesta;
        
    }

    //devuelvo todas las series
    @GetMapping("series")
    //hoy devuelvo una lista de series
    @JsonView({Views.VistaSerie.class})
    public ResponseEntity<List<Serie>> getSeries() {
        
        List<Serie> series = sr.findAll();
        ResponseEntity<List<Serie>> respuesta;

        if(!series.isEmpty()){
            //hoy no pongo el get porque ya tengo la lista
            respuesta = ResponseEntity.ok(series);
        } else {
            respuesta = ResponseEntity.notFound().build();
        }

        return respuesta;
    }
    

    
    
}
