package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.Embeddable;

@Embeddable
public class Categoria {

    double precio;
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    String tipo;
}
