import entity.Customer;
import entity.ItemType;
import entity.Order;
import entity.OrderLine;
import facade.CustomerFacade;
import facade.ItemTypeFacade;
import facade.OrderFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade customerFacade = CustomerFacade.getCustomerFacade(emf);
        ItemTypeFacade itemTypeFacade = ItemTypeFacade.getItemTypeFacade(emf);
        OrderFacade orderFacade = OrderFacade.getOrderFacade(emf);


        Customer customer = new Customer("Mads Brandt", "Some email");
        ItemType itemType = new ItemType("Beer", "Product Description", 20);

        customerFacade.create(customer);
        itemTypeFacade.create(itemType);

        OrderLine orderLine = new OrderLine();
        orderLine.addItemType(itemTypeFacade.getById(1), 4);
        Order order = new Order();

        order.addOrderLine(orderLine);

        orderFacade.create(order, customer.getId());

        System.out.println(orderFacade.getTotalPrice(order.getId()));
    }
}
