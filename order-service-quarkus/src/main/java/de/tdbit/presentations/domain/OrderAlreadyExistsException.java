package de.tdbit.presentations.domain;

public class OrderAlreadyExistsException extends RuntimeException {

    private final String customerNumber;

    public OrderAlreadyExistsException(String customerNumber) {
        super("Order already exists for number: " + customerNumber);
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
