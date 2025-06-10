package es.unican.nelson.polaflix_nelson.dominio;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Visualizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento en BD con h2
    @JsonProperty("id")
    private Long id;

    @JsonProperty("fecha")
    LocalDate fecha;

    @JsonProperty("precio")
    double precio;

     @ManyToOne
    @JsonProperty("usuario")
    @JsonBackReference
    Usuario usuario;

    @JsonProperty("num_capitulo")
    int num_capitulo;

    @JsonProperty("temporada")
    int num_temporada;

    @JsonProperty("nombre_Serie")
    String nombre_serie;

    public int getNum_capitulo() {
        return num_capitulo;
    }

    public void setNum_capitulo(int num_capitulo) {
        this.num_capitulo = num_capitulo;
    }

    public int getNum_temporada() {
        return num_temporada;
    }

    public void setNum_temporada(int num_temporada) {
        this.num_temporada = num_temporada;
    }

    public String getNombre_serie() {
        return nombre_serie;
    }

    public void setNombre_serie(String nombre_serie) {
        this.nombre_serie = nombre_serie;
    }


    //Capitulo capitulo;

     public Visualizacion(Capitulo capitulo, double precio, int num_capitulo, int num_temporada, String nombre_serie) {
        this.fecha = LocalDate.now();
        this.precio = precio;
        this.num_capitulo = num_capitulo;
        this.num_temporada = num_temporada;
        this.nombre_serie = nombre_serie;
    }

    public double getPrecio() {
        return precio;
    }

    

}
