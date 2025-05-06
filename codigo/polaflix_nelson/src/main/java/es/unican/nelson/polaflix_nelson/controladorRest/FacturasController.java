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

@RestController
public class FacturasController {
    
    //creo un objeto del repositorio de facturas
    @Autowired
    FacturaRepository fact;

     //devuelvo todas las facturas
    @GetMapping("facturas")
    //hoy devuelvo una lista de facturas
    public ResponseEntity<List<Factura>> getTodas() {
        
        List<Factura> facturas = fact.findAll();
        ResponseEntity<List<Factura>> respuesta;

        if(!facturas.isEmpty()){
            //hoy no pongo el get porque ya tengo la lista
            respuesta = ResponseEntity.ok(facturas);
        } else {
            respuesta = ResponseEntity.notFound().build();
        }

        return respuesta;
    }

    //devuelvo todos los campos de una factura con ese id
    @GetMapping("facturas/{id}")
    public ResponseEntity<Factura> getFactura(@PathVariable("id") Long idFactura) {
        //ahora recupero primero una factura
        Optional<Factura> f = fact.getFacturaById(idFactura);
        ResponseEntity<Factura> respuesta;
        if(f.isPresent()){
            //el .get me devuelve la serie de dentro
            respuesta = ResponseEntity.ok(f.get());
        } else {
            respuesta = ResponseEntity.notFound().build();
        }

        return respuesta;
        
    }
    
}
