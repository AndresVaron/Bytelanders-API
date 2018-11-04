package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ByteLanders
 */
@Entity
public class ClienteEntity implements Serializable {

    @Id
    private String usuario;

    private String ipModem;

    private String longitud;

    private String latitud;
    
    private String direccion;
    
    private String cedula;
    
    private String direccionCaja;
    
    private String departamento;

    private String localidad;

    private String barrio;
    
    private String longitudCaja;
    
   private String latitudCaja;
   
    private boolean errada;
    
    private boolean usaApp;
    
    private String tipoPredio;
    
    
    

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ipModem
     */
    public String getIpModem() {
        return ipModem;
    }

    /**
     * @param ipModem the ipModem to set
     */
    public void setIpModem(String ipModem) {
        this.ipModem = ipModem;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the direccionCaja
     */
    public String getDireccionCaja() {
        return direccionCaja;
    }

    /**
     * @param direccionCaja the direccionCaja to set
     */
    public void setDireccionCaja(String direccionCaja) {
        this.direccionCaja = direccionCaja;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * @return the barrio
     */
    public String getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

   
    /**
     * @return the errada
     */
    public boolean isErrada() {
        return errada;
    }

    /**
     * @param errada the errada to set
     */
    public void setErrada(boolean errada) {
        this.errada = errada;
    }

    /**
     * @return the usaApp
     */
    public boolean isUsaApp() {
        return usaApp;
    }

    /**
     * @param usaApp the usaApp to set
     */
    public void setUsaApp(boolean usaApp) {
        this.usaApp = usaApp;
    }

    /**
     * @return the longitudCaja
     */
    public String getLongitudCaja() {
        return longitudCaja;
    }

    /**
     * @param longitudCaja the longitudCaja to set
     */
    public void setLongitudCaja(String longitudCaja) {
        this.longitudCaja = longitudCaja;
    }

    /**
     * @return the latitudCaja
     */
    public String getLatitudCaja() {
        return latitudCaja;
    }

    /**
     * @param latitudCaja the latitudCaja to set
     */
    public void setLatitudCaja(String latitudCaja) {
        this.latitudCaja = latitudCaja;
    }

    /**
     * @return the tipoPredio
     */
    public String getTipoPredio() {
        return tipoPredio;
    }

    /**
     * @param tipoPredio the tipoPredio to set
     */
    public void setTipoPredio(String tipoPredio) {
        this.tipoPredio = tipoPredio;
    }
    
    

}
