package facade;
/*
 * author mads
 * version 1.0
 */

import entity.Customer;
import entity.ItemType;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class ItemTypeFacade {

    private static ItemTypeFacade instance;
    private static EntityManagerFactory emf;

    private ItemTypeFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ItemTypeFacade getItemTypeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ItemTypeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ItemType create(ItemType itemtype) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(itemtype);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return itemtype;
    }

    public List<ItemType> getAll() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT I FROM ItemType I", ItemType.class).getResultList();
    }

    public List<ItemType> getByName(String name) {
        EntityManager em = getEntityManager();
        TypedQuery<ItemType> query = em.createQuery(
                "SELECT i FROM ItemType i WHERE i.name LIKE CONCAT('%', :name, '%')",
                ItemType.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public ItemType getById(long id) {
        EntityManager em = getEntityManager();
        return em.find(ItemType.class, id);
    }
}
