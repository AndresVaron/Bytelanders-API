/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.BusquedaEntity;
import entities.ClienteEntity;
import entities.DatosClienteCompradoEntity;
import exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.BusquedaPersistence;
import persistence.ClientePersistence;
import persistence.DatosClienteCompradoPersistence;

/**
 *
 * @author ByteLanders
 */
@Stateless
public class BusquedaLazyLogic {

    private static final Logger LOGGER = Logger.getLogger(GeoActualizacionLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence;

    @Inject
    private BusquedaPersistence busquedaPersistence;
    
    @Inject
    private DatosClienteCompradoPersistence datosCompradoPersistence;

    @Inject
    private  DireccionErradaLogic dirErradaLogic;
    
    public ClienteEntity calcularDireccion(ClienteEntity cliente) throws BusinessLogicException {
        DatosClienteCompradoEntity datos =datosCompradoPersistence.find(cliente.getCedula());
        String[] direcciones = datos.getDireccionPredio().split(",");
        for (int i = 0; i < direcciones.length; i++) {
            if(dirErradaLogic.predioEnRangoCaja(direcciones[i], cliente.getDireccionCaja(), cliente.getLocalidad(), cliente.getDepartamento())==true){
              cliente.setDireccion(direcciones[i]);
              cliente.setErrada(true);
                clientePersistence.update(cliente);
                i=direcciones.length;
                
            }
        }
       LOGGER.log(Level.INFO, "Saliendo del proceso de actualizar calcular Direccion");
        return cliente;
    }

    public List<BusquedaEntity> getBusquedas() {
        LOGGER.info("Inicia proceso de consultar todas las busquedas");
        List<BusquedaEntity> busquedas = busquedaPersistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las busquedas");
        return busquedas;
    }
}
