/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.BusquedaDTO;
import dtos.GeoDTO;
import entities.BusquedaEntity;
import entities.GeoActualizadoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

    /**
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray las verificaciones que se actualizaron.
     */
    @GET
    public List<GeoDTO> getBusqueda() {
        LOGGER.info("DireccionErradaResource getBusquedas: input: void");
        List<GeoDTO> listaActualizaciones = listEntity2DTO(geoLogic.getActualizaciones());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaActualizaciones.toString());
        return listaActualizaciones;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BookEntity a una lista de
     * objetos BookDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<GeoDTO> listEntity2DTO(List<GeoActualizadoEntity> entityList) {
        List<GeoDTO> list = new ArrayList<>();
        for (GeoActualizadoEntity entity : entityList) {
            list.add(new GeoDTO(entity));
        }
        return list;
    }

}
