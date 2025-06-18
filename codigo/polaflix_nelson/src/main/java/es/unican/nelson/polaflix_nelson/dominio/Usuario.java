package es.unican.nelson.polaflix_nelson.dominio;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.nelson.polaflix_nelson.controladorRest.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento en BD con h2
    @JsonProperty("idUsuario")
    @JsonView({Views.VistaUsuario.class, Views.VistaFactura.class})
    private Long id;

    @JsonProperty("tarifaPlana")
    @JsonView({Views.VistaUsuario.class})
    protected boolean tarifaPlana; //true es que el usuario tiene la cuota mensual
    
    @JsonProperty("nombre")
    @JsonView({Views.VistaUsuario.class, Views.VistaFactura.class})
    protected String nombreUsuario;

    @JsonProperty("contrasenia")
    @JsonView({Views.VistaUsuario.class})
    protected String contrasenia;

    @OneToMany
    @JsonProperty("series empezadas")
    @JsonView({Views.VistaUsuario.class})
    protected List<Serie> empezadas;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty("series pendientes")
    @JsonView({Views.VistaUsuario.class})
    protected List<Serie> pendientes;

    @OneToMany
    @JsonProperty("series terminadas")
    @JsonView({Views.VistaUsuario.class})
    protected List<Serie> terminadas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonProperty("visualizaciones")
    @JsonManagedReference
    @JsonView({Views.VistaUsuario.class})
    protected List<Visualizacion> visualizaciones;
    
    @OneToOne
    @JsonProperty("cuenta de banco")
    @JsonView({Views.VistaUsuario.class})
    protected CuentaBancaria cuentaBanco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonProperty("facturas")
    @JsonBackReference
    @JsonView({Views.VistaUsuario.class})
    protected List<Factura> facturas;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapKey(name = "id")
    @JsonView({Views.VistaUsuario.class})
    protected Map<Serie, CapituloID> capituloMasAltoVisualizado;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("factura actual")
    @JsonView({Views.VistaUsuario.class})
    protected Factura facturaActual;

    //necesito el constructor vacio para recuperar de la base de datos
    protected Usuario(){

    }

    public Usuario(String nombre) {
        this.nombreUsuario = nombre;
        this.tarifaPlana = false;
        this.capituloMasAltoVisualizado = new HashMap<>();

        empezadas = new ArrayList<>();
        pendientes = new ArrayList<>();
        terminadas = new ArrayList<>();
        visualizaciones = new ArrayList<>();
        facturas = new ArrayList<>();
    }

    public void activarTarifaPlana() {
        this.tarifaPlana = true;
    }

    public void desactivarTarifaPlana() {
        this.tarifaPlana = false;
    }

    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<Serie> getEmpezadas() {
        return empezadas;
    }

    public void setEmpezadas(List<Serie> empezadas) {
        this.empezadas = empezadas;
    }

    public List<Serie> getPendientes() {
        return pendientes;
    }

    public void setPendientes(List<Serie> pendientes) {
        this.pendientes = pendientes;
    }

    public List<Serie> getTerminadas() {
        return terminadas;
    }

    public void setTerminadas(List<Serie> terminadas) {
        this.terminadas = terminadas;
    }

    public List<Visualizacion> getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(List<Visualizacion> visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public CuentaBancaria getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(CuentaBancaria cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Map<Serie, CapituloID> getCapituloMasAltoVisualizado() {
        return capituloMasAltoVisualizado;
    }

    public void setCapituloMasAltoVisualizado(Map<Serie, CapituloID> capituloMasAltoVisualizado) {
        this.capituloMasAltoVisualizado = capituloMasAltoVisualizado;
    }

    public Factura getFacturaActual() {
        return facturaActual;
    }

    public void setFacturaActual(Factura facturaActual) {
        this.facturaActual = facturaActual;
    }

    public void verCapitulo(Capitulo capitulo, CapituloID capitulo_id) {

        //reviso si ya se ha visto el capitulo 
        Serie serie = capitulo.getTemporada().getSerie();

        //CapituloID capitulo_id;

        //obtengo el ultimo capitulo visualizado de esa serie
        if(capituloMasAltoVisualizado.get(serie) == null){
            //capitulo_id = new CapituloID(capitulo.getId());
            capituloMasAltoVisualizado.put(serie, capitulo_id);
        }

        long id_ultimo_capitulo = capituloMasAltoVisualizado.get(serie).getId();
        //ahora obtengo el capitulo en si
        Capitulo c = serie.getCapituloById(id_ultimo_capitulo);
        //ahora comparo
        if(c.getTemporada().getNumeroTemporada() < capitulo.getTemporada().getNumeroTemporada()){
            
            //como la temporada es mayor, pues hay que sustituir
            //capitulo_id = new CapituloID(capitulo.getId());
            capituloMasAltoVisualizado.put(serie, capitulo_id);

            
        } //ahora comparamos el caso de que el numero de temporada sea igual
        else if(c.getTemporada().getNumeroTemporada() == capitulo.getTemporada().getNumeroTemporada()){

            //veo si el nuevo capitulo es mayor al mayor que se habia visto al momento
            if(c.getNumeroCapitulo() < capitulo.getNumeroCapitulo()){
                //capitulo_id = new CapituloID(capitulo.getId());
                capituloMasAltoVisualizado.put(serie, capitulo_id);
            }
        }


        
        //tengo que recorrer toddas las facturas para que si un capitulo que vi el mes pasado 
        //no me lo vuelva a cobrar si lo veo en la factura del siguiente mes
        for (Factura factura : this.facturas){

            for(Visualizacion visualizacion : factura.getItems()){

                //veo si coincide nombre de serie
                if(visualizacion.getNombre_serie().equals(serie.getTitulo())){
                    //veo si coincide el num temporada
                    if(visualizacion.getNum_temporada() == capitulo.getTemporada().getNumeroTemporada()){

                        if(visualizacion.getNum_capitulo() == capitulo.getNumeroCapitulo()){
                            
                            //si todo esto coincide significa que el capitulo ya fue visto en alguna factura pasada
                            //asi que NO hay que hacer nada
                            return;
                        }
                    }
                }
            }
        }

        //mover la serie a la lista que corresponda
        if(pendientes.contains(serie)){
            pendientes.remove(serie);
            //la anhado a empezadas
            empezadas.add(serie);
        }

        //si el capitulo es el ultimo, pues muevo de empezadas a terminadas
        if(capitulo.ultimoCapitulo){

            //hay que poner este if para no eliminar de empezadas 2 o mas veces una serie que ya se ha visto su ultimo capitulo
            if(empezadas.contains(serie)){
                empezadas.remove(serie);
                terminadas.add(serie);
            }
        }

        //obtener factura actual o generar una nueva

        double precio = 0.0;
        if (!tarifaPlana) {
            precio = capitulo.temporada.serie.categoria.getPrecio();
        }

        LocalDate fechaActual = LocalDate.now();

        //veo si la fecha actual no coincide con el mes o con el anhio, pues cambia la factual actual y debo crear una nueva
        if(facturaActual == null || facturaActual.getFecha().getYear() != fechaActual.getYear() || facturaActual.getFecha().getMonth() != fechaActual.getMonth()){

            //creo una nueva factura
            Factura factura_nueva = new Factura(this, fechaActual);
            //la anhado a la lista de facturas
            facturas.add(factura_nueva);
            //actualizo la factura actual
            facturaActual = factura_nueva;
        }

        //generar el nuevo item de la factura
        Visualizacion nuevaVisualizacion = new Visualizacion(capitulo, precio, capitulo.getNumeroCapitulo(), capitulo.getTemporada().getNumeroTemporada(), capitulo.getTemporada().getSerie().getTitulo());
        visualizaciones.add(nuevaVisualizacion);

        facturaActual.getItems().add(nuevaVisualizacion);
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
