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
@Table(name = "ORDER_LINE")
@NamedQuery(name = "OrderLine.deleteAllRows", query = "DELETE FROM OrderLine")
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITEMTYPE_FK")
    private ItemType itemType;

    public void addItemType (ItemType itemType, int quantity) {
        this.itemType = itemType;
        this.quantity = quantity;
    }
}
