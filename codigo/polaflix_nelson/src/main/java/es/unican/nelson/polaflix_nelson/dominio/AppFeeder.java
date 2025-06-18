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
        //nelson.activarTarifaPlana();

        Categoria estandar = new Categoria(0.50, "estandar");
        Categoria silver = new Categoria(0.75, "silver");
        Categoria gold = new Categoria(1.50, "gold");

        Serie pokemon = new Serie("pokemon rubi", gold, "ratas dandose hostias");
        Serie invencible = new Serie("invencible", gold, "superheroes");
        Serie star = new Serie("star wars", silver, "yo soy tu padre");
        Serie ben10 = new Serie("ben10", silver, "ben 10");
        Serie naruto  = new Serie("naruto", silver, "rasengan");

        Temporada season1 = new Temporada(1, pokemon);
        Temporada season1Inv = new Temporada(1, invencible);
        Temporada season1Star = new Temporada(1, star);
        Temporada season1Ben = new Temporada(1, ben10);
        Temporada season2Ben = new Temporada(2, ben10);
        Temporada season1Naruto = new Temporada(1, naruto);

        Capitulo cap1 = new Capitulo(true, "oh por ahi no", "cuidado por donde vas", 1, season1);
        Capitulo cap1Inv = new Capitulo(false, "vuela alto", "no tan alto", 1, season1Inv);
        Capitulo cap1Star = new Capitulo(false, "el regreso del jedi", "he's back", 1, season1Star);
        Capitulo cap1Ben = new Capitulo(false, "reloj", "he's back", 1, season1Ben);
        Capitulo cap2Ben = new Capitulo(false, "reloj mas grande", "he's not back", 1, season2Ben);
        Capitulo cap1Naruto = new Capitulo (false, "no te vayas", "no me fui", 1, season1Naruto);
        
        Factura fact1 = new Factura(nelson, LocalDate.now());
        nelson.setFacturaActual(fact1);
        //fact1.anhadirItem(cap1);

        season1.anhandirCapitulo(cap1);
        season1Inv.anhandirCapitulo(cap1Inv);
        season1Star.anhandirCapitulo(cap1Star);
        season1Ben.anhandirCapitulo(cap1Ben);
        season2Ben.anhandirCapitulo(cap2Ben);
        season1Naruto.anhandirCapitulo(cap1Naruto);

        pokemon.anhandirTemporada(season1);
        invencible.anhandirTemporada(season1Inv);
        star.anhandirTemporada(season1Star);
        ben10.anhandirTemporada(season1Ben);
        ben10.anhandirTemporada(season2Ben);
        naruto.anhandirTemporada(season1Naruto);

        //esto me guarda todo de golpe
        series.save(pokemon);
        series.save(invencible);
        series.save(star);
        series.save(ben10);
        series.save(naruto);
        CapituloID capId1 = new CapituloID(cap1.getId(), cap1.getTemporada().getSerie());
        CapituloID capIDInv1 = new CapituloID(cap1Inv.getId(), cap1Inv.getTemporada().getSerie());
        CapituloID capIdStar1 = new CapituloID(cap1Star.getId(), cap1Star.getTemporada().getSerie());
        CapituloID capIdBen1 = new CapituloID(cap1Ben.getId(), cap1Ben.getTemporada().getSerie());
        CapituloID capIdBen2 = new CapituloID(cap2Ben.getId(), cap2Ben.getTemporada().getSerie());
        CapituloID capIdNaruto = new CapituloID(cap1Naruto.getId(), cap1Naruto.getTemporada().getSerie());
        
        capituloID.save(capId1);
        capituloID.save(capIDInv1);
        capituloID.save(capIdStar1);
        capituloID.save(capIdBen1);
        capituloID.save(capIdBen2);
        capituloID.save(capIdNaruto);


        nelson.getPendientes().add(pokemon);
        nelson.getPendientes().add(invencible);
        nelson.getPendientes().add(star);     
        nelson.getPendientes().add(ben10);
        nelson.getPendientes().add(naruto);
        usuarios.save(nelson);

        facturas.save(fact1);
        nelson.verCapitulo(cap1, capId1);
        nelson.verCapitulo(cap1Star, capIdStar1);
        nelson.verCapitulo(cap1Inv,capIDInv1);
        nelson.getFacturaActual().setFecha(LocalDate.of(2025,4,3));
        usuarios.save(nelson);
        nelson.verCapitulo(cap1Ben, capIdBen1);
        nelson.verCapitulo(cap2Ben, capIdBen2);
        usuarios.save(nelson);
        facturas.save(fact1);
    }

}
