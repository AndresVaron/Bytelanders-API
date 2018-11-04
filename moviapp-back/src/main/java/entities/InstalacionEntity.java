package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ByteLanders
 */
@Entity
public class InstalacionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String direccion;

    private String departamento;

    private String localidad;

    private String barrio;
    
    private String direccionCaja;
    
    private String coordenadaCaja;
    
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
     * @return the coordenadaCaja
     */
    public String getCoordenadaCaja() {
        return coordenadaCaja;
    }

    /**
     * @param coordenadaCaja the coordenadaCaja to set
     */
    public void setCoordenadaCaja(String coordenadaCaja) {
        this.coordenadaCaja = coordenadaCaja;
    }

}
