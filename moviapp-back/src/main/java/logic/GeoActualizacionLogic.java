/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.BusquedaEntity;
import entities.ClienteEntity;
import entities.GeoActualizadoEntity;
import exceptions.BusinessLogicException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.ClientePersistence;
import persistence.GeoActualizadoPersistence;

/**
 *
 * @author ByteLanders
 */
@Stateless
public class GeoActualizacionLogic {

    private static final Logger LOGGER = Logger.getLogger(GeoActualizacionLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence;

    @Inject
    private GeoActualizadoPersistence geoPersistence;

    public void actualizacionGeografica(String longitud, String latitud, String correo, String ip) throws BusinessLogicException {
    	
        ClienteEntity cliente = clientePersistence.find(correo);
        
        if(cliente == null) {
        	throw new BusinessLogicException("El cliente que se ha intentado verificar no existe");
        }
        
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar las coordenas geograficas");
        
        if (cliente.isErrada()) {        	
        	LOGGER.log(Level.INFO, "Posicion Diferente");
            //Se simula la verificacion de que la ip del cliente esta en el rango de ips de su modem.
            if (cliente.getIpModem().equals(ip)) {
                LOGGER.log(Level.INFO, "Cliente esta en su casa");
                cliente.setLatitud(latitud);
                cliente.setLongitud(longitud);
                GeoActualizadoEntity geoActualizado = new GeoActualizadoEntity();
                geoActualizado.setLatitud(latitud);
                geoActualizado.setLongitud(longitud);
                
                //Tipo Predio 
                String tipoPredio = null;
                
                //TODO, determinar tipo de predio. Esto también se debe realizar si la dirección
                //se determina correcta en el gran recorrido inicial.
                
                
                
                geoActualizado.setTipoPredio(tipoPredio);
                //TODO, cliente set tipo de predio. En algún otro lado también???
                //Se ha actualizado la dirección. Ahora no debe estar errada.
                cliente.setErrada(false);
                
                geoPersistence.create(geoActualizado);
                clientePersistence.update(cliente);
                LOGGER.log(Level.INFO, "Fin de actualizacion geoLocalizacion");    
            }
            
        }
        LOGGER.log(Level.INFO, "Saliendo del proceso de actualizar coordenadas geograficas.");
    }

    public List<GeoActualizadoEntity> getActualizaciones() {
        LOGGER.info("Inicia proceso de consultar todas las actualizaciones");
        List<GeoActualizadoEntity> actualizaciones = geoPersistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las actualizaciones");
        return actualizaciones;
    }

}
