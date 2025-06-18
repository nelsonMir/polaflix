package es.unican.nelson.polaflix_nelson.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.nelson.polaflix_nelson.controladorRest.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Capitulo {

        @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento en BD con h2
    @JsonProperty("idCapitulo")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    private Long id;
    
    @JsonProperty("ultimoCapitulo")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    boolean ultimoCapitulo;

    @JsonProperty("titulo")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    String titulo;

    @JsonProperty("descripcion")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    String descripcion;

    @JsonProperty("numeroCapitulo")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    int numeroCapitulo;

    @ManyToOne
    Temporada temporada;

    public Capitulo(){}

    public Capitulo(boolean ultimoCapitulo, String titulo, String descripcion, int numeroCapitulo, Temporada temporada){

        this.ultimoCapitulo = ultimoCapitulo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.numeroCapitulo = numeroCapitulo;
        this.temporada = temporada;
    }

    public boolean isUltimoCapitulo() {
        return ultimoCapitulo;
    }
    public void setUltimoCapitulo(boolean ultimoCapitulo) {
        this.ultimoCapitulo = ultimoCapitulo;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }
    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }
    public Temporada getTemporada() {
        return temporada;
    }
    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        Capitulo other = (Capitulo) obj;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

    


}
