package entity;
/*
 * author mads
 * version 1.0
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@NamedQuery(name = "Customer.deleteAllRows", query = "DELETE FROM Customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Order> orderList = new ArrayList<>();

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addOrder (Order order) {
        this.orderList.add(order);
        order.setCustomer(this);
    }
}
