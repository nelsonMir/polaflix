package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.nelson.polaflix_nelson.controladorRest.Views;

@Entity
public class Serie {
        @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento en BD con h2
    @JsonProperty("idSerie")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    private Long id;

    @JsonProperty("titulo")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    String titulo;

    @Embedded
    @JsonProperty("categoria")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    Categoria categoria;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonProperty("temporada")
    @JsonManagedReference  //esto para evitar bucles con las referencias en doble direccion
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    List<Temporada> temporadas;

    @JsonProperty("sinopsis")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    String sinopsis;

    //se usa el elementColelction ncunado sse mapea un tipo embeebido
    @ElementCollection
    @JsonProperty("creadores")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    protected List<Artista> creadores;

    @ElementCollection
    @JsonProperty("actores")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    protected List<Artista> actores;
    

    public Serie(){

    }

    public Serie(String titulo, Categoria categoria, String sinopsis){
        this.titulo = titulo;
        this.categoria = categoria;
        this.sinopsis = sinopsis;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Serie other = (Serie) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

    

}
