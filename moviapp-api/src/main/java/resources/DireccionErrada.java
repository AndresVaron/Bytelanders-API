/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.BusquedaDTO;
import entities.BusquedaEntity;
import exceptions.BusinessLogicException;

import java.io.IOException;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import logic.DireccionErradaLogic;

/**
 *
 * @author ByteLanders
 */
/**
 *
 * @author ByteLanders
 */
@Path("direccionerrada")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DireccionErrada {

    private static final Logger LOGGER = Logger.getLogger(DireccionErrada.class.getName());

    @Inject
    private DireccionErradaLogic direccionErrada; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Processa una direccion equivocada y retorna las as coordenadas que
     *
     * @param direccion la direccion que se va a analizar.
     * @return Objeto que representa la respuesta de la busqueda.
     */
    @GET
    @Path("dir")
    public BusquedaDTO calcularDireccion(@QueryParam("direccion") String direccion, @QueryParam("localidad") String localidad, @QueryParam("departamento") String departamento) {
        LOGGER.log(Level.INFO, "DireccionErradaResource direccion: input: {0}", direccion);
        return new BusquedaDTO(direccionErrada.calcularDireccion(direccion, localidad, departamento));
    }

    @POST
    @Path("deterradas")
    public void determinarDireccionesIncorrectas() throws BusinessLogicException, IOException {
    	
    	LOGGER.log(Level.INFO, "Determinando direcciones erradas.");
    	direccionErrada.asignarErrados();
    }
    
    @POST
    @Path("estimarCatastro")
    public void estimarNuevaDireccionConCatastro() throws BusinessLogicException {
    	LOGGER.log(Level.INFO, "Determinando direcciones con catastro.");
    	direccionErrada.recalcularInfoClientesCompras();
    }
    
    /**
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<BusquedaDTO> getBusqueda() {
        LOGGER.info("DireccionErradaResource getBusquedas: input: void");
        List<BusquedaDTO> listaBusquedas = listEntity2DTO(direccionErrada.getBusquedas());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaBusquedas.toString());
        return listaBusquedas;
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
    private List<BusquedaDTO> listEntity2DTO(List<BusquedaEntity> entityList) {
        List<BusquedaDTO> list = new ArrayList<>();
        for (BusquedaEntity entity : entityList) {
            list.add(new BusquedaDTO(entity));
        }
        return list;
    }

}
