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
@Table(name = "\"ORDER\"")
@NamedQuery(name = "Order.deleteAllRows", query = "DELETE FROM Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_FK")
    private Customer customer;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ORDER_FK")
    List<OrderLine> orderLines = new ArrayList<>();

    public void addOrderLine (OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }
}
