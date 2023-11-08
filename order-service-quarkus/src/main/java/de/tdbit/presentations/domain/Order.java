package de.tdbit.presentations.domain;

import de.tdbit.presentations.domain.customer.Customer;
import de.tdbit.presentations.domain.item.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order implements Serializable {

    private UUID id;
    private String orderNumber;
    private String customerNumber;
    private final List<String> itemNumbers = new ArrayList<>();
    private Customer customer;
    private final List<Item> items = new ArrayList<>();

    protected Order() {
        // for framework
    }

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public UUID getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public List<String> getItemNumbers() {
        return itemNumbers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void add(Customer customer) {
        if (customer != null) {
            this.customer = customer;
            setCustomerNumber(customer.getCustomerNumber());
        }
    }

    public void add(Item item) {
        if (item != null && !items.contains(item)) {
            this.items.add(item);
            this.itemNumbers.add(item.getItemNumber());
        }
    }

    void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    void addItemNumber(String itemNumber) {
        this.itemNumbers.add(itemNumber);
    }

    void prepareToStore() {
        this.id = UUID.randomUUID();
        this.customer = null;
        this.items.clear();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Order order)) return false;
        return Objects.equals(getOrderNumber(), order.getOrderNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNumber());
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", customerNumber='" + customerNumber + "'"
                + ", itemNumbers='" + itemNumbers + "'"
                + "}";
    }
}
