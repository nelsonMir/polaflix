package es.unican.nelson.polaflix_nelson.dominio;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.nelson.polaflix_nelson.repositories.CapituloIDRepository;
import es.unican.nelson.polaflix_nelson.repositories.FacturaRepository;
import es.unican.nelson.polaflix_nelson.repositories.SerieRepository;
import es.unican.nelson.polaflix_nelson.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

@Component
public class AppFeeder implements CommandLineRunner{

    @Autowired
    UsuarioRepository usuarios;

    @Autowired
    SerieRepository series;

    @Autowired
    FacturaRepository facturas;

    @Autowired
    CapituloIDRepository capituloID;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");

        Usuario nelson = new Usuario("Nelson");

        Categoria estandar = new Categoria(0.50, "estandar");
        Categoria silver = new Categoria(0.75, "silver");
        Categoria gold = new Categoria(1.50, "gold");

        Serie pokemon = new Serie("pokemon rubi", gold, "ratas dandose hostias");

        Temporada season1 = new Temporada(1, pokemon);

        Capitulo cap1 = new Capitulo(false, "oh por ahi no", "cuidado por donde vas", 1, season1);
        
        Factura fact1 = new Factura(nelson, LocalDate.now());
        //fact1.anhadirItem(cap1);

        season1.anhandirCapitulo(cap1);
        pokemon.anhandirTemporada(season1);

        //esto me guarda todo de golpe
        series.save(pokemon);
        CapituloID capId1 = new CapituloID(cap1.getId(), cap1.getTemporada().getSerie());
        capituloID.save(capId1);
        usuarios.save(nelson);

        facturas.save(fact1);
    }

}
