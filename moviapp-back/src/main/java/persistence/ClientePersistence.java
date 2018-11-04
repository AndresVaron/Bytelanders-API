package persistence;

import entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Stateless
public class ClientePersistence {

    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviAppPU");

    EntityManager em = emf.createEntityManager();

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param clienteEntity objeto cliente que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ClienteEntity create(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Creando un cliente nuevo");
        em.getTransaction().begin();
        em.persist(clienteEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de crear un cliente nuevo");
        return clienteEntity;
    }

    /**
     * Devuelve todos los clientes de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de
     * datos
     */
    public List<ClienteEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los clientes");
        TypedQuery query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza un cliente.
     *
     * @param clienteEntity: el cliente que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un cliente con los cambios aplicados.
     */
    public ClienteEntity update(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Actualizando cliente con usuario = {0}", clienteEntity.getUsuario());
        LOGGER.log(Level.INFO, "ERRADA: " + clienteEntity.isErrada());
        em.getTransaction().begin();
        clienteEntity = em.merge(clienteEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de actualizar el cliente con usuario = {0}", clienteEntity.getUsuario());
        return clienteEntity;
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
        ClienteEntity entity = find(clientesUsuario);
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
    public ClienteEntity find(String clientesUsuario) {
        LOGGER.log(Level.INFO, "Consultando cliente con usuario = {0}", clientesUsuario);
        return em.find(ClienteEntity.class, clientesUsuario);
    }

    public List<ClienteEntity> findByDireccion(String direccion, String localidad, String departamento) {
        LOGGER.log(Level.INFO, "Consultando busquedas por direccion ", direccion);
        TypedQuery query = em.createQuery("Select e from ClienteEntity e where e.direccion = :direccion and e.localidad = :localidad and e.departamento = :departamento", ClienteEntity.class);
        query = query.setParameter("direccion", direccion);
        query = query.setParameter("localidad", localidad);
        query = query.setParameter("departamento", departamento);
        
        LOGGER.log(Level.INFO, query.toString());
        
        List<ClienteEntity> sameDireccion = query.getResultList();
        LOGGER.log(Level.INFO, "Saliendo de consultar busquedas por direccion ", direccion);
        return sameDireccion;
    }

}
