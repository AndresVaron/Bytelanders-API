package persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.BusquedaEntity;
import entities.ClienteCompraEntity;

public class ClienteCompraPersistence {

	 private static final Logger LOGGER = Logger.getLogger(BusquedaPersistence.class.getName());

	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviAppPU");
	    EntityManager em = emf.createEntityManager();
	    
	    
	    public ClienteCompraEntity create(ClienteCompraEntity compraEntity) {
	    	LOGGER.log(Level.INFO, "Creando cliente compra persistence");
	        em.getTransaction().begin();
	        em.persist(compraEntity);
	        em.getTransaction().commit();
	        LOGGER.log(Level.INFO, "Creando cliente compra creado");
	        return compraEntity;
	    }
	    
	    public List<ClienteCompraEntity> findComprasPendientes() {
	        LOGGER.log(Level.INFO, "Consultando todas las cliente compra persistence");
	        Query q = em.createQuery("select u from BusquedaEntity u where comprado = false");
	        return q.getResultList();
	    }
	
        public ClienteCompraEntity update(ClienteCompraEntity clienteEntity) {
        	LOGGER.log(Level.INFO, "Actualizando cliente con usuario = {0}", clienteEntity.getCliente());
        	em.getTransaction().begin();
        	clienteEntity = em.merge(clienteEntity);
        	em.getTransaction().commit();
        	LOGGER.log(Level.INFO, "Saliendo de actualizar el cliente con usuario = {0}", clienteEntity.getCliente());
        	return clienteEntity;
    }
}
