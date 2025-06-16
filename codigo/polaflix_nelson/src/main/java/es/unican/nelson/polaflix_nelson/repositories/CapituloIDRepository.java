package es.unican.nelson.polaflix_nelson.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.nelson.polaflix_nelson.dominio.Capitulo;
import es.unican.nelson.polaflix_nelson.dominio.CapituloID;

public interface CapituloIDRepository extends JpaRepository<CapituloID, Long>{
    
    public Optional<CapituloID> getCapituloIDById (Long id);

   
}
