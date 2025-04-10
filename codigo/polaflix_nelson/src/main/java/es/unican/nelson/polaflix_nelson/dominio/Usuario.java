package es.unican.nelson.polaflix_nelson.dominio;
import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento en BD con h2
    private Long id;

    protected boolean tarifaPlana; //true es que el usuario tiene la cuota mensual
    protected String nombreUsuario;
    protected String contrasenia;
    @OneToMany
    protected ArrayList<Serie> empezadas;
    @OneToMany
    protected ArrayList<Serie> pendientes;
    @OneToMany
    protected ArrayList<Serie> terminadas;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    protected ArrayList<Visualizacion> visualizaciones;
    @OneToOne
    protected CuentaBancaria cuentaBanco;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    protected ArrayList<Factura> facturas;
    @OneToMany
    @MapKey(name = "id")
    protected Map<Serie, CapituloID> capituloMasAltoVisualizado;
    @OneToOne
    protected Factura facturaActual;

    //necesito el constructor vacio para recuperar de la base de datos
    protected Usuario(){

    }

    public Usuario(String nombre) {
        this.nombreUsuario = nombre;
        this.tarifaPlana = false;
        this.capituloMasAltoVisualizado = new HashMap<>();
    }

    public void activarTarifaPlana() {
        this.tarifaPlana = true;
    }

    public void desactivarTarifaPlana() {
        this.tarifaPlana = false;
    }

    public void verCapitulo(Capitulo capitulo) {
        if (capituloMasAltoVisualizado.containsKey(capitulo.temporada.serie)) {
            System.out.println("Ya viste este capítulo, se vuelve a ver pero no se vuelve a cobrar");
            return;
        }

        double precio = 0.0;
        if (!tarifaPlana) {
            //precio = capitulo.temporada.serie.categoria.getPrecio();
        }

        //añado la visualizacion al array de visualizaciones
        Visualizacion nuevaVisualizacion = new Visualizacion(capitulo, precio);
        visualizaciones.add(nuevaVisualizacion);

        //creo el nuevo objeto capituloID
        CapituloID capituloID = new CapituloID();
        capituloMasAltoVisualizado.put(capitulo.temporada.serie, capituloID);

        System.out.println("Viendo capítulo: " + capitulo.getTitulo() +
                           " | Precio: " + precio);
        
        facturaActual.items.add(nuevaVisualizacion);
    }

    /**
	 * Lanzada si la serie ya esta comenzada o terminada
	 */
	@SuppressWarnings("serial")
	public static class serieYaComenzada extends RuntimeException {
	}  

    public void agregarSerie(Serie serie) throws serieYaComenzada{

        if(empezadas.contains(serie) || terminadas.contains(serie)){
            throw new serieYaComenzada();
        }
        pendientes.add(serie);

    }
}
