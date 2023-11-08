package de.tdbit.presentations.domain.item;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static de.tdbit.presentations.domain.item.Item.*;

@Entity
@Table(name = "ITEM")
@Access(AccessType.FIELD)
@NamedQuery(name = GET_ALL, query = "select item from Item item")
@NamedQuery(name = FIND_BY_ITEM_NUMBER, query = "select item from Item item where item.itemNumber = :" + PARAM_ITEM_NUMBER)
@NamedQuery(name = CHECK_EXIST_BY_ITEM_NUMBER, query = "select item.itemNumber from Item item where item.itemNumber = :" + PARAM_ITEM_NUMBER)
@NamedQuery(name = DELETE_BY_ITEM_NUMBER, query = "delete from Item item where item.itemNumber = :" + PARAM_ITEM_NUMBER)
public class Item implements Serializable {

    public static final String GET_ALL = "Item.getAllItems";
    public static final String FIND_BY_ITEM_NUMBER = "Item.findByItemNumber";
    public static final String CHECK_EXIST_BY_ITEM_NUMBER = "Item.checkExistByItemNumber";
    public static final String DELETE_BY_ITEM_NUMBER = "Item.deleteByItemNumber";
    public static final String PARAM_ITEM_NUMBER = "ItemNumber";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ITEM_NUMBER", nullable = false, unique = true)
    private String itemNumber;
    @Column(name = "ITEM_DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SubItem> subItems = new ArrayList<>();

    protected Item() {
        // for framework
    }

    public Item(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getDescription() {
        return description;
    }

    public List<SubItem> getSubItems() {
        return subItems;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void add(SubItem subItem) {
        if (subItem != null) {
            subItem.bindTo(this);
            subItems.add(subItem);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(getItemNumber(), item.getItemNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemNumber());
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", itemNumber='" + itemNumber + "'"
                + ", description='" + description + "'"
                + ", subItems=" + subItems
                + "}";
    }
}
