package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class CapituloID {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento en BD con h2
    protected long id;

    @ManyToOne
    protected Serie serie;

    public CapituloID(){}

    public CapituloID(long id, Serie serie){

        this.id = id;
        this.serie = serie;
    }
    
    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public long getId() {
        return id;
    }

}
