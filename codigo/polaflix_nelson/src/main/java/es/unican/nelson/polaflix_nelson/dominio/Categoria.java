package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.Embeddable;

@Embeddable
public class Categoria {

    double precio;
    String tipo;
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
    public Categoria(double precio, String tipo) {
        this.precio = precio;
        this.tipo = tipo;
    }

    public Categoria(){}
    
    
}
