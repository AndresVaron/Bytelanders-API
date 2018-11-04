package persistence;

import entities.BusquedaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Busquedas. Se conecta a través del
 * Entity Manager de javax.persistance con la base de datos SQL.
 *
 * @author ByteLanders
 */
@Stateless
public class BusquedaPersistence {

    private static final Logger LOGGER = Logger.getLogger(BusquedaPersistence.class.getName());

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviAppPU");
    EntityManager em = emf.createEntityManager();

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param busquedaEntity objeto busqueda que se creará en la base de datos
     * @return devuelve la entidad creada con una id dado por la base de datos.
     */
    public BusquedaEntity create(BusquedaEntity busquedaEntity) {
        LOGGER.log(Level.INFO, "Creando una busqueda nueva");
        em.getTransaction().begin();
        em.persist(busquedaEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Busqueda creada");
        return busquedaEntity;
    }

    /**
     * Devuelve todas las busquedas de la base de datos.
     *
     * @return una lista con todas las busquedas que encuentre en la base de
     * datos.
     */
    public List<BusquedaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las busquedas");
        Query q = em.createQuery("select u from BusquedaEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay alguna busqueda con el id que se envía de argumento
     *
     * @param busquedasId: id correspondiente a la busqueda buscada.
     * @return una busqueda.
     */
    public BusquedaEntity find(Long busquedasId) {
        LOGGER.log(Level.INFO, "Consultando la busqueda con id={0}", busquedasId);
        return em.find(BusquedaEntity.class, busquedasId);
    }

    /**
     * Actualiza una busqueda.
     *
     * @param busquedaEntity: la busqueda que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una busqueda con los cambios aplicados.
     */
    public BusquedaEntity update(BusquedaEntity busquedaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la busqueda con id={0}", busquedaEntity.getId());
        em.getTransaction().begin();
        busquedaEntity = em.merge(busquedaEntity);
        em.getTransaction().commit();

        return busquedaEntity;
    }

    /**
     *
     * Borra una busqueda de la base de datos recibiendo como argumento el id
     * del busqueda
     *
     * @param busquedasId: id correspondiente a la busqueda a borrar.
     */
    public void delete(Long busquedasId) {
        LOGGER.log(Level.INFO, "Borrando el busqueda con id={0}", busquedasId);
        em.getTransaction().begin();
        BusquedaEntity busquedaEntity = em.find(BusquedaEntity.class, busquedasId);
        em.remove(busquedaEntity);
        em.getTransaction().commit();
    }

    /**
     * Busca si hay alguna busqueda con la longitud y la latidud que se envía de
     * argumento
     *
     * @param direccion direcion buscada
     * @return null si no existe ningun busqueda con el isbn del argumento. Si
     * existe alguno devuelve el primero.
     */
    public BusquedaEntity findByDireccion(String direccion) {
        LOGGER.log(Level.INFO, "Consultando busquedas por direccion ", direccion);
        TypedQuery query = em.createQuery("Select e From BusquedaEntity e where e.direccion = :direccion", BusquedaEntity.class);
        query = query.setParameter("direccion", direccion);
        List<BusquedaEntity> sameDireccion = query.getResultList();
        BusquedaEntity result;
        if (sameDireccion == null) {
            result = null;
        } else if (sameDireccion.isEmpty()) {
            result = null;
        } else {
            result = sameDireccion.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar busquedas por direccion ", direccion);
        return result;
    }
}
