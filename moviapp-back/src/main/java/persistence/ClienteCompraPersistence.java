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
	        LOGGER.log(Level.INFO, "Creando una busqueda nueva");
	        em.getTransaction().begin();
	        em.persist(compraEntity);
	        em.getTransaction().commit();
	        LOGGER.log(Level.INFO, "Busqueda creada");
	        return compraEntity;
	    }
	    
	    public List<BusquedaEntity> findComprasPendientes() {
	        LOGGER.log(Level.INFO, "Consultando todas las busquedas");
	        Query q = em.createQuery("select u from BusquedaEntity u where comprado = false");
	        return q.getResultList();
	    }
	
}
