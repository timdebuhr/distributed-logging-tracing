package de.tdbit.presentations.domain.item;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "SUB_ITEM")
public class SubItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "SUB_ITEM_NUMBER", nullable = false)
    private String subItemNumber;
    @Column(name = "SUB_ITEM_DESCRIPTION")
    private String subItemDescription;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private Item item;

    protected SubItem() {
        // for framework
    }

    public SubItem(String subItemNumber) {
        this.subItemNumber = subItemNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getSubItemNumber() {
        return subItemNumber;
    }

    public String getSubItemDescription() {
        return subItemDescription;
    }

    public Item getItem() {
        return item;
    }

    public void setSubItemDescription(String subItemDescription) {
        this.subItemDescription = subItemDescription;
    }

    void bindTo(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "SubItem{"
                + "id=" + id
                + ", subItemNumber='" + subItemNumber + "'"
                + ", subItemDescription='" + subItemDescription + "'"
                + ", itemNumber=" + item.getItemNumber()
                + "}";
    }
}
