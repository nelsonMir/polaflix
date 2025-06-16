package es.unican.nelson.polaflix_nelson.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.nelson.polaflix_nelson.dominio.Visualizacion;

public interface VisualizacionRepository extends JpaRepository<Visualizacion, Long>{

    public Optional<Visualizacion> getVisualizacionById (Long id);
}
