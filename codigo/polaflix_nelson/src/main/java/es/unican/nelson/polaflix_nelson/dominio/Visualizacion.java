package es.unican.nelson.polaflix_nelson.dominio;

import java.time.LocalDate;

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
    private Long id;
    LocalDate fecha;
    double precio;
    @ManyToOne
    Usuario usuario;
    Capitulo capitulo;

     public Visualizacion(Capitulo capitulo, double precio) {
        this.fecha = LocalDate.now();
        this.precio = precio;
        this.capitulo = capitulo;
    }

    public double getPrecio() {
        return precio;
    }

    public Capitulo getCapitulo() {
        return capitulo;
    }

}
