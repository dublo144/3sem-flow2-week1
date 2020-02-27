package entity;
/*
 * author mads
 * version 1.0
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ITEM_TYPE")
@NamedQuery(name = "ItemType.deleteAllRows", query = "DELETE FROM ItemType")
public class ItemType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "itemType", cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines;

    private String name;
    private String description;
    private double price;

    public ItemType(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
