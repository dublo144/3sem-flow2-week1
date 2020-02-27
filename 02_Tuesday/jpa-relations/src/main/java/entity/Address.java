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
@Table(name = "address")
@NamedQuery(name = "Address.deleteAllRows", query = "DELETE FROM Address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String road;
    private String no;

    public Address(String road, String no) {
        this.road = road;
        this.no = no;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="CustomerAddress",
            joinColumns=@JoinColumn(name="ADDRESS_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="CUSTOMER_ID", referencedColumnName="ID"))
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customer) {
        this.customers = customer;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
