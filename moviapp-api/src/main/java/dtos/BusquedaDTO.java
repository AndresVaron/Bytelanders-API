/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.BusquedaEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author ByteLanders
 */
public class BusquedaDTO {

    private String direccion;

    private String longitud;

    private String latitud;

    private String tipoPredio;
    
    private boolean actualizada;

    public BusquedaDTO() {

    }

    /**
     * Constructor a partir de la entidad
     *
     * @param busquedaEntity La entidad de la busqueda
     */
    public BusquedaDTO(BusquedaEntity busquedaEntity) {
        if (busquedaEntity != null) {
            this.direccion = busquedaEntity.getDireccion();
            this.latitud = busquedaEntity.getLatitud();
            this.longitud = busquedaEntity.getLongitud();
            this.tipoPredio = busquedaEntity.getTipoPredio();
            this.actualizada = busquedaEntity.getActualizada();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
     * @return the actualizada
     */
    public boolean isActualizada() {
        return actualizada;
    }

    /**
     * @param actualizada the actualizada to set
     */
    public void setActualizada(boolean actualizada) {
        this.actualizada = actualizada;
    }

}
