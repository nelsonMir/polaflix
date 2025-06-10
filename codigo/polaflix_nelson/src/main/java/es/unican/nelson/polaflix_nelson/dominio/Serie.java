package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Serie {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento en BD con h2
    @JsonProperty("id")
    private Long id;

    @JsonProperty("titulo")
    String titulo;

    Categoria categoria;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonProperty("temporada")
    @JsonManagedReference  //esto para evitar bucles con las referencias en doble direccion
    List<Temporada> temporadas;

    @JsonProperty("sinopsis")
    String sinopsis;

    public Serie(){

    }

    public Serie(String titulo){
        this.titulo = titulo;
        //debo inicializar la lista de temporadas porque sino sera nula
        temporadas = new ArrayList<>();
    }

    public void anhandirTemporada(Temporada temporada){
        temporadas.add(temporada);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Capitulo getCapituloById(long id){

        //me recorro todas las temporadasy todos los capitulos para buscar uno que coincida con el id
        for(Temporada t : temporadas){

            for(Capitulo c : t.getCapitulos()){

                if(c.getId() == id){

                    return c;
                }
            }
        }

        return null;
    }

}
