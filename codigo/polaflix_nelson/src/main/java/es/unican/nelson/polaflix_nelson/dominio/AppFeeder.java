package es.unican.nelson.polaflix_nelson.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class AppFeeder implements CommandLineRunner{

    @Autowired
    UsuarioRepository usuarios;

    @Autowired
    SerieRepository series;

    @Autowired
    FacturaRepository facturas;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");

        Usuario nelson = new Usuario("Nelson");

        Serie pokemon = new Serie("pokemon rubi");

        Temporada season1 = new Temporada(1, pokemon);

        Capitulo cap1 = new Capitulo(false, "oh por ahi no", "cuidado por donde vas", 1, season1);

        Factura fact1 = new Factura(nelson);
        //fact1.anhadirItem(cap1);

        season1.anhandirCapitulo(cap1);
        pokemon.anhandirTemporada(season1);

        //esto me guarda todo de golpe
        series.save(pokemon);

        usuarios.save(nelson);

        facturas.save(fact1);
    }

}
