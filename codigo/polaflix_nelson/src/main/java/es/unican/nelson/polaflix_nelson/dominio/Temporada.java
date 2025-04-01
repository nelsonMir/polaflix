package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


import java.util.*;


@Entity
public class Temporada {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento en BD con h2
    private Long id;

    int numeroTemporada;
    @ManyToOne
    Serie serie;
    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL)
    List<Capitulo> Capitulos;

    public int getNumeroTemporada() {
        return numeroTemporada;
    }
    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }
    
}
