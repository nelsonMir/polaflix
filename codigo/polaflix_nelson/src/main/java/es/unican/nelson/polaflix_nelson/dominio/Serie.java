package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

@Entity
public class Serie {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento en BD con h2
    private Long id;

    String titulo;

    //Categoria categoria;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    List<Temporada> temporadas;

    /*public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }*/

}
