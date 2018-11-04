/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.BusquedaDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @POST
    @Path("{direccion}")
    public BusquedaDTO calcularDireccion(@PathParam("direccion") String direccion) {
        LOGGER.log(Level.INFO, "DireccionErradaResource direccion: input: {0}", direccion);
        return new BusquedaDTO(direccionErrada.calcularDireccion(direccion));
    }


}
