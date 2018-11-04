package entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Clase que representa una Busqueda en la persistencia y permite su
 * serialización
 *
 * @author ByteLanders
 */
@Entity
public class BusquedaEntity extends BaseEntity implements Serializable {

    private String direccion;

    private String longitud;

    private String latitud;

    private String tipoPredio;

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

}
