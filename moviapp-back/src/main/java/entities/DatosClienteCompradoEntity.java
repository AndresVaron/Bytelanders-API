package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ByteLanders
 */
@Entity
public class DatosClienteCompradoEntity implements Serializable {

    
    private String nombre;

    @Id
    private String cedula;

    private String direccionPredio;

    private String departamento;
    
    private String ciudad;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the direccionPredio
     */
    public String getDireccionPredio() {
        return direccionPredio;
    }

    /**
     * @param direccionPredio the direccionPredio to set
     */
    public void setDireccionPredio(String direccionPredio) {
        this.direccionPredio = direccionPredio;
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
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

  

}
