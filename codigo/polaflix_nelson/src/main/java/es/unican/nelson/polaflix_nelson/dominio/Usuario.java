package es.unican.nelson.polaflix_nelson.dominio;
import java.time.LocalDate;
import java.util.*;

public class Usuario {
    protected boolean tarifaPlana; //true es que el usuario tiene la cuota mensaul
    protected String nombreUsuario;
    protected String contrasenia;
    protected ArrayList<Serie> empezadas;
    protected ArrayList<Serie> pendientes;
    protected ArrayList<Serie> terminadas;
    protected Visualizacion[] visualizaciones;
    protected CuentaBancaria cuentaBanco;
    protected Factura[] facturas;
    protected Map<Capitulo, Visualizacion> historialVisualizaciones;
    protected Factura facturaActual;

    public Usuario(String nombre) {
        this.nombreUsuario = nombre;
        this.tarifaPlana = false;
        this.historialVisualizaciones = new HashMap<>();
    }

    public void activarTarifaPlana() {
        this.tarifaPlana = true;
    }

    public void desactivarTarifaPlana() {
        this.tarifaPlana = false;
    }

    public void verCapitulo(Capitulo capitulo) {
        if (historialVisualizaciones.containsKey(capitulo)) {
            System.out.println("Ya viste este capítulo.");
            return;
        }

        double precio = 0.0;
        if (!tarifaPlana) {
            precio = capitulo.temporada.serie.categoria.getPrecio();
        }

        Visualizacion nuevaVisualizacion = new Visualizacion(capitulo, precio);
        historialVisualizaciones.put(capitulo, nuevaVisualizacion);

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
