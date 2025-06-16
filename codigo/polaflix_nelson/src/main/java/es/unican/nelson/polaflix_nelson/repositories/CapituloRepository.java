package es.unican.nelson.polaflix_nelson.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.nelson.polaflix_nelson.dominio.Capitulo;


public interface CapituloRepository extends JpaRepository<Capitulo, Long>{
    
    public Optional<Capitulo> getCapituloById (Long id);

    public List<Capitulo> findAll();
}
