package de.tdbit.presentations.domain.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item implements Serializable {

    private String itemNumber;
    private String description;
    private final List<SubItem> subItems = new ArrayList<>();

    protected Item() {
        // for framework
    }

    public Item(String itemNumber) {
        this.itemNumber = itemNumber;
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
        this.subItems.add(subItem);
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
                + "itemNumber='" + itemNumber + "'"
                + ", description='" + description + "'"
                + ", subItems=" + subItems
                + "}";
    }
}
