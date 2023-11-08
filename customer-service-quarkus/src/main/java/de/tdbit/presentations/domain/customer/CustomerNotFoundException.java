package de.tdbit.presentations.domain.customer;

public class CustomerNotFoundException extends RuntimeException {

    private final String customerNumber;

    public CustomerNotFoundException(String customerNumber) {
        super("No customer found for number: " + customerNumber);
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

}
