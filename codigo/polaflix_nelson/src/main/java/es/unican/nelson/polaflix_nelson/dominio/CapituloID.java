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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        CapituloID other = (CapituloID) obj;
        if (id != other.id)
            return false;
        return true;
    }

    
}
