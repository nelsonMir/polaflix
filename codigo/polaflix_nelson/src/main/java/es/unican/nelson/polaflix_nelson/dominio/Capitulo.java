package es.unican.nelson.polaflix_nelson.dominio;

public class Capitulo {

    boolean ultimoCapitulo;
    String titulo;
    String descripcion;
    int numeroCapitulo;
    Temporada temporada;
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
