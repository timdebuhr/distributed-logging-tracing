package de.tdbit.presentations.domain.customer;

import de.tdbit.presentations.domain.customer.address.Address;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    private String customerNumber;
    private String addressNumber;
    private String name;
    private Address address;

    protected Customer() {
        // for framework
    }

    public Customer(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void add(Address address) {
        if (address != null) {
            this.address = address;
            setAddressNumber(address.getAddressNumber());
        }
    }

    void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Customer customer)) return false;
        return Objects.equals(getCustomerNumber(), customer.getCustomerNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerNumber());
    }

    @Override
    public String toString() {
        return "Customer{"
                + "customerNumber='" + customerNumber + "'"
                + ", addressNumber='" + addressNumber + "'"
                + "}";
    }
}
