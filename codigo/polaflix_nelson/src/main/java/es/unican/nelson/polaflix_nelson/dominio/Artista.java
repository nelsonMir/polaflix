package es.unican.nelson.polaflix_nelson.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Embeddable
public class Artista {
    
    @JsonProperty("nombre")
    String nombre;
    @JsonProperty("apellidos")
    String apellidos;

    public Artista(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Artista(){}

}
