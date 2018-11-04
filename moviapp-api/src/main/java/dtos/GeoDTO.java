/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.GeoActualizadoEntity;

/**
 * Clase que extiende de {@link AuthorDTO} para manejar las relaciones entre los
 * AuthorDTO y otros DTOs. Para conocer el contenido de un Autor vaya a la
 * documentacion de {@link AuthorDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "longitud": string,
 *      "latitud": string,
 *      "ip": string,
 *      "correo": string
 *   }
 * </pre> Por ejemplo un objeto de verificacion se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "longitud": 1389.23423,
 *      "latitud": 12323.234,
 *      "ip": 1.1.1.1,
 *      "correo": hola@absdkjbasd.com
 *   }
 *
 * </pre>
 *
 * @author ByteLanders
 */
public class GeoDTO {

    private String longitud;

    private String latitud;

    private String ip;

    private String correo;

    public GeoDTO() {

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
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public GeoDTO(GeoActualizadoEntity entity) {
        if (entity != null) {
            this.latitud = entity.getLatitud();
            this.longitud = entity.getLongitud();
        }
    }
}
