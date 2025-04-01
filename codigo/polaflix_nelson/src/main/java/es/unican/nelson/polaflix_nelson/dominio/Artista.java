package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.Embeddable;

@Embeddable
public class Artista {

    String nombre;
    String apellidos;
    Serie[] seriesActor;
    Serie[] seriesDirector;



}
