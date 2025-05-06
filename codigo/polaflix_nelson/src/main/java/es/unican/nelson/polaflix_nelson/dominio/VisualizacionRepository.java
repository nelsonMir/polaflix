package es.unican.nelson.polaflix_nelson.dominio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VisualizacionRepository extends JpaRepository<Visualizacion, Long>{

    public Optional<Visualizacion> getVisualizacionById (Long id);
}
