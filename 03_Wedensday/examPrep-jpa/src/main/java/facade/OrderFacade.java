package facade;
/*
 * author mads
 * version 1.0
 */

import entity.Customer;
import entity.Order;
import entity.OrderLine;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class OrderFacade {

    private static OrderFacade instance;
    private static EntityManagerFactory emf;

    private OrderFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static OrderFacade getOrderFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Order create(Order order, Long customerId) {
        EntityManager em = getEntityManager();
        Customer customer = em.find(Customer.class, customerId);
        try {
            em.getTransaction().begin();
            customer.addOrder(order);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return order;
    }

    public List<Order> getOrdersFromCustomer (Long customerId) {
        EntityManager em = getEntityManager();
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    public int getTotalPrice (Long orderId) {
        EntityManager em = getEntityManager();
        Order order = em.find(Order.class, orderId);
        int total = 0;
        for (OrderLine orderLine : order.getOrderLines()) {
            total += orderLine.getItemType().getPrice() * orderLine.getQuantity();
        }
        return total;
    }
}
