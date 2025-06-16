package es.unican.nelson.polaflix_nelson.controladorRest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import es.unican.nelson.polaflix_nelson.repositories.SerieRepository;
import es.unican.nelson.polaflix_nelson.service.CapituloService;


@RestController
public class CapitulosController {
    
    @Autowired
    SerieRepository serie;

    @PostMapping("capitulos")
    //aqui pongo los parametros que vienen en el header
    public String postVerCapitulo(@RequestHeader("serie-name") String serie, @RequestHeader("num-temp") int temp, @RequestHeader("num-cap") int cap) {
        
        CapituloService servicio = new CapituloService();
        String resultado = "todo mal";

        if(servicio.verCapitulo(serie, temp, cap)){
            resultado = "Todo correcto";
        }

        return resultado;
    }
    
}
