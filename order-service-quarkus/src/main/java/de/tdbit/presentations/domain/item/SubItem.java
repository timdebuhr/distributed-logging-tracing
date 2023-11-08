package de.tdbit.presentations.domain.item;

import java.io.Serializable;

public class SubItem implements Serializable {

    private String subItemNumber;
    private String subItemDescription;

    protected SubItem() {
        // for framework
    }

    public SubItem(String subItemNumber) {
        this.subItemNumber = subItemNumber;
    }

    public String getSubItemNumber() {
        return subItemNumber;
    }

    public String getSubItemDescription() {
        return subItemDescription;
    }

    public void setDescription(String subItemDescription) {
        this.subItemDescription = subItemDescription;
    }

    @Override
    public String toString() {
        return "SubItem{"
                + "subItemNumber='" + subItemNumber + "'"
                + ", subItemDescription='" + subItemDescription + "'"
                + "}";
    }
}
