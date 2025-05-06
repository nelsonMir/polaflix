package es.unican.nelson.polaflix_nelson.dominio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//el repo <clase, el tipo del id>
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    //el optional es por si me devuelve null
    public Optional<Usuario> getUsuarioByNombreUsuario (String nombreUsuario);

    public Optional<Usuario> getUsuarioById (Long id);

    public List<Usuario> findAll();
}
