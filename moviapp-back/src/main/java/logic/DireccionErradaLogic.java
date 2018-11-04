/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.BusquedaEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.BusquedaPersistence;
import persistence.ClientePersistence;

/**
 *
 * @author ByteLanders
 */
@Stateless
public class DireccionErradaLogic {

    private static final Logger LOGGER = Logger.getLogger(GeoActualizacionLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence;

    @Inject
    private BusquedaPersistence busquedaPersistence;

    public BusquedaEntity calcularDireccion(String direccion) {
        BusquedaEntity busqueda = new BusquedaEntity();
        busqueda = busquedaPersistence.create(busqueda);
        LOGGER.log(Level.INFO, "Saliendo del proceso de actualizar calcular Direccion");
        return busqueda;
    }
}
