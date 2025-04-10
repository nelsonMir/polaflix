package es.unican.nelson.polaflix_nelson.dominio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long>{

    public Optional<Factura> getFacturaById (Long id);

}
