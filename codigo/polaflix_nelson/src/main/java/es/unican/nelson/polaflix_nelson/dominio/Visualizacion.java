package es.unican.nelson.polaflix_nelson.dominio;

import java.time.LocalDate;

public class Visualizacion {

    LocalDate fecha;
    double precio;
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
