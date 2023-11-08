package de.tdbit.presentations.domain.item;

public class ItemAlreadyExistsException extends RuntimeException {

    private final String itemNumber;

    public ItemAlreadyExistsException(String itemNumber) {
        super("Item already exists for number: " + itemNumber);
        this.itemNumber = itemNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }
}
