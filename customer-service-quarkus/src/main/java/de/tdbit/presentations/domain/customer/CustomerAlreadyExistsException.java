package de.tdbit.presentations.domain.customer;

public class CustomerAlreadyExistsException extends RuntimeException {

    private final String customerNumber;

    public CustomerAlreadyExistsException(String customerNumber) {
        super("Customer already exists for number: " + customerNumber);
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
