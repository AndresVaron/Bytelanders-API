/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.GeoDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import logic.GeoActualizacionLogic;

/**
 *
 * @author ByteLanders
 */
@Path("geoactualizacion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class GeoActualizacion {

    private static final Logger LOGGER = Logger.getLogger(GeoActualizacion.class.getName());

    @Inject
    private GeoActualizacionLogic geoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Verifica que las coordenadas con la informacion que se recibe en el
     * cuerpo de la petición y si son diferentes actualiza la base de datos.
     *
     * @param geo clase que representa el objeto ingresado por parametro.
     */
    @POST
    public void verificacion(GeoDTO geo) {
        LOGGER.log(Level.INFO, "GeoActualizacionResource verificacion: input: {0}", geo);
        geoLogic.actualizacionGeografica(geo.getLongitud(), geo.getLatitud(), geo.getCorreo(), geo.getIp());
    }

}
