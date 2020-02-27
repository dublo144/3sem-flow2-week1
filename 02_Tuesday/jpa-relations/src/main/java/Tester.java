import entity.Address;
import entity.Customer;
import facade.CustomerFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        Address address1 = new Address("Some Road", "24");

        Customer customer1 = new Customer();
        customer1.setFirstName("Mads");
        customer1.setLastName("Brandt");
        customer1.setHobbies(Arrays.asList("Some", "Hobby"));
        customer1.addPhone("443322", "Some Other Descr");
        customer1.addAddress(address1);

        Customer customer2 = new Customer("Oscar", "Laurberg");
        customer2.setHobbies(Arrays.asList("Some", "Other", "Hobby"));
        customer2.addPhone("112233", "Some Descr");
        customer2.addAddress(address1);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");

        CustomerFacade FACADE = CustomerFacade.getCustomerFacade(entityManagerFactory);
        FACADE.create(customer1);
        // FACADE.addCustomer(customer2);
    }
}
