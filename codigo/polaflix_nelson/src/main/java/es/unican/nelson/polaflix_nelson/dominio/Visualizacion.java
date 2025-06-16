package es.unican.nelson.polaflix_nelson.dominio;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.nelson.polaflix_nelson.controladorRest.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Visualizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento en BD con h2
    @JsonProperty("idVisualizacion")
    @JsonView({Views.VistaFactura.class})
    private Long id;

    @JsonProperty("fechaVisualizacion")
    @JsonView({Views.VistaFactura.class})
    LocalDate fecha;

    @JsonProperty("precio")
    @JsonView({Views.VistaFactura.class})
    double precio;

     @ManyToOne
    @JsonProperty("usuario")
    @JsonBackReference
    Usuario usuario;

    @JsonProperty("numCapitulo")
    @JsonView({Views.VistaFactura.class})
    int num_capitulo;

    @JsonProperty("temporada")
    @JsonView({Views.VistaFactura.class})
    int num_temporada;

    @JsonProperty("nombreSerie")
    @JsonView({Views.VistaFactura.class})
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


     public Visualizacion(Capitulo capitulo, double precio, int num_capitulo, int num_temporada, String nombre_serie) {
        this.fecha = LocalDate.now();
        this.precio = precio;
        this.num_capitulo = num_capitulo;
        this.num_temporada = num_temporada;
        this.nombre_serie = nombre_serie;
    }

    public Visualizacion(){}

    public double getPrecio() {
        return precio;
    }

    

}
