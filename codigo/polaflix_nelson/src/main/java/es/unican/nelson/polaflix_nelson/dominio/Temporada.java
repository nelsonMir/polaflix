package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Temporada {

        @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento en BD con h2
    private Long id;

    int numeroTemporada;
    @ManyToOne
    @JsonBackReference
    Serie serie;
    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL)
    List<Capitulo> capitulos;

    public Temporada(){}

    public Temporada(int numeroTemporada, Serie serie){
        this.numeroTemporada = numeroTemporada;
        this.serie = serie;
        capitulos = new ArrayList<>();
    }

    //anhadir capitulos lo hago con un metodo y no el constructuro
    public void anhandirCapitulo(Capitulo capitulo){
        capitulos.add(capitulo);
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }
    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    
    
}
