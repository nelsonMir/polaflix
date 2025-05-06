package es.unican.nelson.polaflix_nelson.dominio;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty("id")
    private Long id;

    @JsonProperty("tarifaPlana")
    protected boolean tarifaPlana; //true es que el usuario tiene la cuota mensual
    
    @JsonProperty("nombre")
    protected String nombreUsuario;

    @JsonProperty("contrasenia")
    protected String contrasenia;

    @OneToMany
    @JsonProperty("series empezadas")
    protected List<Serie> empezadas;

    @OneToMany
    @JsonProperty("series pendientes")
    protected List<Serie> pendientes;

    @OneToMany
    @JsonProperty("series terminadas")
    protected List<Serie> terminadas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonProperty("visualizaciones")
    @JsonManagedReference
    protected List<Visualizacion> visualizaciones;
    
    @OneToOne
    @JsonProperty("cuenta de banco")
    protected CuentaBancaria cuentaBanco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonProperty("facturas")
    @JsonBackReference
    protected List<Factura> facturas;

    @OneToMany
    @MapKey(name = "id")
    protected Map<Serie, CapituloID> capituloMasAltoVisualizado;
    
    @OneToOne
    @JsonProperty("factura actual")
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
