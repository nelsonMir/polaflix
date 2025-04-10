package es.unican.nelson.polaflix_nelson.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class AppFeeder implements CommandLineRunner{

    @Autowired
    UsuarioRepository usuarios;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");

        Usuario nelson = new Usuario("Nelson");

        usuarios.save(nelson);
    }

}
