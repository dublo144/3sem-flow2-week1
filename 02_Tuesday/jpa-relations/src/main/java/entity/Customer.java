package entity;
/*
 * author mads
 * version 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@NamedQuery(name = "Customer.deleteAllRows", query = "DELETE FROM Customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ElementCollection
    @CollectionTable(
            name="Hobbies",
            joinColumns=@JoinColumn(name="CUSTOMER_ID")
    )
    private List<String> hobbies = new ArrayList();

    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "PHONE")
    @Column(name = "Description")
    private Map<String,String> phones = new HashMap();

    public void addPhone(String phoneNO, String description) {
        phones.put(phoneNO, description);
    }

    public String getPhoneDescription (String phoneNo) {
        return phones.get(phoneNo);
    }

    // JPA Entity Mappings
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    private List<Address> addresses = new ArrayList<>();

    public void addAddress (Address address) {
        addresses.add(address);
        address.addCustomer(this);
    }
}
