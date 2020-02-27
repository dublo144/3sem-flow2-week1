package facade;
/*
 * author mads
 * version 1.0
 */

import entity.Customer;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    private CustomerFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer getCustomer(long id) {
        EntityManager em = getEntityManager();
        return em.find(Customer.class, id);
    }

    public List<Customer> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Customer> query = em.createQuery("SELECT C FROM Customer C", Customer.class);
        return query.getResultList();
    }

    public void deleteCustomer (int id) {
        EntityManager em = getEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            em.getTransaction().begin();
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void create(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
