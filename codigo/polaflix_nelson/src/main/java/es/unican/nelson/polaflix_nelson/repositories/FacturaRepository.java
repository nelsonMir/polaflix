package es.unican.nelson.polaflix_nelson.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.nelson.polaflix_nelson.dominio.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long>{

    public Optional<Factura> getFacturaById(Long id);

    public List<Factura> findAll();
}
