package es.unican.nelson.polaflix_nelson.service;

import org.springframework.beans.factory.annotation.Autowired;

import es.unican.nelson.polaflix_nelson.dominio.Capitulo;
import es.unican.nelson.polaflix_nelson.dominio.Serie;
import es.unican.nelson.polaflix_nelson.dominio.SerieRepository;
import es.unican.nelson.polaflix_nelson.dominio.Temporada;
import es.unican.nelson.polaflix_nelson.dominio.Usuario;
import es.unican.nelson.polaflix_nelson.dominio.UsuarioRepository;
import jakarta.transaction.Transactional;

public class CapituloService {

    @Autowired
    UsuarioRepository usuarioRepo;

    @Autowired
    SerieRepository serieRepo;

    public CapituloService(){}

    @Transactional //aqui va la logica de negocio
    public boolean verCapitulo(String serieTitulo, int temporadaNum, int capituloNum){

        Usuario usuarioUnico = usuarioRepo.getUsuarioByNombreUsuario("nelson").get(); //como devuelve un optional hay que hacer un .get

        Serie serie = serieRepo.getSerieByTitulo(serieTitulo).get();
        boolean retorno = false;

        for(Temporada t: serie.getTemporadas() ){
            if(t.getNumeroTemporada() == temporadaNum){
                for(Capitulo c: t.getCapitulos()){
                    if(c.getNumeroCapitulo() == capituloNum){
                        usuarioUnico.verCapitulo(c);
                        retorno = true;
                        break;
                    }
                }
                break;
            }
        }

        usuarioRepo.save(usuarioUnico);

        return retorno;
    }
    
}
