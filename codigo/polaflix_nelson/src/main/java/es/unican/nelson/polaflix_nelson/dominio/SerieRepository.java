package es.unican.nelson.polaflix_nelson.dominio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long>{

    public Optional<Serie> getSerieById (Long id);

    public Optional<Serie> getSerieByTitulo(String titulo);

    public List<Serie> findAll();
}
