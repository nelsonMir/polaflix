package es.unican.nelson.polaflix_nelson.dominio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long>{

    public Optional<Factura> getSerieById (Long id);
}
