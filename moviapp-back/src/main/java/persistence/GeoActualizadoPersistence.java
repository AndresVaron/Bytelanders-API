package persistence;

import entities.GeoActualizadoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Clase que maneja la persistencia para GeoActualizados. Se conecta a través del
 * Entity Manager de javax.persistance con la base de datos SQL.
 *
 * @author ByteLanders
 */
@Stateless
public class GeoActualizadoPersistence {

    private static final Logger LOGGER = Logger.getLogger(GeoActualizadoPersistence.class.getName());

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviAppPU");
    EntityManager em = emf.createEntityManager();

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param geoactualizadoEntity objeto geoactualizado que se creará en la base de datos
     * @return devuelve la entidad creada con una id dado por la base de datos.
     */
    public GeoActualizadoEntity create(GeoActualizadoEntity geoactualizadoEntity) {
        LOGGER.log(Level.INFO, "Creando una geoactualizado nueva");
        em.persist(geoactualizadoEntity);
        LOGGER.log(Level.INFO, "GeoActualizado creada");
        return geoactualizadoEntity;
    }

    /**
     * Devuelve todas las geoactualizados de la base de datos.
     *
     * @return una lista con todas las geoactualizados que encuentre en la base de
     * datos.
     */
    public List<GeoActualizadoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las geoactualizados");
        Query q = em.createQuery("select u from GeoActualizadoEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay alguna geoactualizado con el id que se envía de argumento
     *
     * @param geoactualizadosId: id correspondiente a la geoactualizado buscada.
     * @return una geoactualizado.
     */
    public GeoActualizadoEntity find(Long geoactualizadosId) {
        LOGGER.log(Level.INFO, "Consultando la geoactualizado con id={0}", geoactualizadosId);
        return em.find(GeoActualizadoEntity.class, geoactualizadosId);
    }

    /**
     * Actualiza una geoactualizado.
     *
     * @param geoactualizadoEntity: la geoactualizado que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una geoactualizado con los cambios aplicados.
     */
    public GeoActualizadoEntity update(GeoActualizadoEntity geoactualizadoEntity) {
        LOGGER.log(Level.INFO, "Actualizando la geoactualizado con id={0}", geoactualizadoEntity.getId());
        return em.merge(geoactualizadoEntity);
    }

    /**
     *
     * Borra una geoactualizado de la base de datos recibiendo como argumento el id
     * del geoactualizado
     *
     * @param geoactualizadosId: id correspondiente a la geoactualizado a borrar.
     */
    public void delete(Long geoactualizadosId) {
        LOGGER.log(Level.INFO, "Borrando el geoactualizado con id={0}", geoactualizadosId);
        GeoActualizadoEntity geoactualizadoEntity = em.find(GeoActualizadoEntity.class, geoactualizadosId);
        em.remove(geoactualizadoEntity);
    }
}
