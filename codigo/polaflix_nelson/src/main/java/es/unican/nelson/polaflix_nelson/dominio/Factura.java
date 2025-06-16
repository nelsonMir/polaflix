package es.unican.nelson.polaflix_nelson.dominio;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento en BD con h2
    @JsonProperty("id")
    private Long id;

    @OneToMany
    @JsonManagedReference
    protected List<Visualizacion> items;

    @ManyToOne
    @JsonProperty("usuario")
    protected Usuario usuario;
    
    @JsonProperty("fecha")
    protected LocalDate fecha;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Factura(){

    }

    public Factura(Usuario usuario, LocalDate fecha){
        this.usuario = usuario;
        items = new ArrayList<>();

        this.fecha = fecha;

    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Visualizacion> getItems() {
        return items;
    }
    public void setItems(ArrayList<Visualizacion> items) {
        this.items = items;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
}
