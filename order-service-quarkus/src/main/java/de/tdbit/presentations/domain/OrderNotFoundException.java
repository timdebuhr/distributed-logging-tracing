package de.tdbit.presentations.domain;

public class OrderNotFoundException extends RuntimeException {

    private final String customerNumber;

    public OrderNotFoundException(String customerNumber) {
        super("No order found for number: " + customerNumber);
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

}
