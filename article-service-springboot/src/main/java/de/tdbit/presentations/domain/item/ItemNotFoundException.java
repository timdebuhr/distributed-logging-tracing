package de.tdbit.presentations.domain.item;

public class ItemNotFoundException extends RuntimeException {

    private final String itemNumber;

    public ItemNotFoundException(String itemNumber) {
        super("No item found for number: " + itemNumber);
        this.itemNumber = itemNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }

}
