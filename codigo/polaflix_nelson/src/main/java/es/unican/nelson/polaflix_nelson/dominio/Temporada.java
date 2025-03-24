package es.unican.nelson.polaflix_nelson.dominio;

public class Temporada {

    int numeroTemporada;
    Serie serie;
    public int getNumeroTemporada() {
        return numeroTemporada;
    }
    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }
    public Serie getSerie() {
        return serie;
    }
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

}
