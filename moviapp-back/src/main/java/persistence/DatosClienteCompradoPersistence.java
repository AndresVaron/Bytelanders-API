package persistence;

import entities.DatosClienteCompradoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Stateless
public class DatosClienteCompradoPersistence {

    private static final Logger LOGGER = Logger.getLogger(DatosClienteCompradoPersistence.class.getName());

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviAppPU");

    EntityManager em = emf.createEntityManager();

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param DatosClienteCompradoEntity objeto cliente que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public DatosClienteCompradoEntity create(DatosClienteCompradoEntity DatosClienteCompradoEntity) {
        LOGGER.log(Level.INFO, "Creando un cliente nuevo");
        em.getTransaction().begin();
        em.persist(DatosClienteCompradoEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de crear un cliente nuevo");
        return DatosClienteCompradoEntity;
    }

    /**
     * Devuelve todos los clientes de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de
     * datos
     */
    public List<DatosClienteCompradoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los clientes");
        TypedQuery query = em.createQuery("select u from DatosClienteCompradoEntity u", DatosClienteCompradoEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza un cliente.
     *
     * @param DatosClienteCompradoEntity: el cliente que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un cliente con los cambios aplicados.
     */
    public DatosClienteCompradoEntity update(DatosClienteCompradoEntity DatosClienteCompradoEntity) {
        LOGGER.log(Level.INFO, "Actualizando cliente con nombre = {0}", DatosClienteCompradoEntity.getNombre());
        em.getTransaction().begin();
        DatosClienteCompradoEntity = em.merge(DatosClienteCompradoEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de actualizar el cliente con nombre = {0}", DatosClienteCompradoEntity.getNombre());
        return DatosClienteCompradoEntity;
    }

    /**
     *
     * Borra un cliente de la base de datos recibiendo como argumento el id de
     * el cliente
     *
     * @param clientesUsuario: usuario correspondiente a el cliente a borrar.
     */
    public void delete(String clientesUsuario) {
        LOGGER.log(Level.INFO, "Borrando cliente con usuario = {0}", clientesUsuario);
        em.getTransaction().begin();
        DatosClienteCompradoEntity entity = find(clientesUsuario);
        em.remove(entity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de borrar el cliente con usuario = {0}", clientesUsuario);
    }

    /**
     * Busca si hay algun cliente con el usuario que se envía de argumento
     *
     * @param clientesUsuario: id correspondiente a el cliente buscado.
     * @return un cliente.
     */
    public DatosClienteCompradoEntity find(String clientesUsuario) {
        LOGGER.log(Level.INFO, "Consultando cliente con usuario = {0}", clientesUsuario);
        return em.find(DatosClienteCompradoEntity.class, clientesUsuario);
    }

}
