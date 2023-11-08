package de.tdbit.presentations.domain.customer;

import de.tdbit.presentations.domain.address.Address;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import static de.tdbit.presentations.domain.customer.Customer.*;

@Entity
@Table(name = "CUSTOMER")
@Access(AccessType.FIELD)
@NamedQuery(name = GET_ALL, query = "select customer from Customer customer")
@NamedQuery(name = FIND_BY_CUSTOMER_NUMBER, query = "select customer from Customer customer where customer.customerNumber = :" + PARAM_CUSTOMER_NUMBER)
@NamedQuery(name = CHECK_EXIST_BY_CUSTOMER_NUMBER, query = "select customer.customerNumber from Customer customer where customer.customerNumber = :" + PARAM_CUSTOMER_NUMBER)
@NamedQuery(name = DELETE_BY_CUSTOMER_NUMBER, query = "delete from Customer customer where customer.customerNumber = :" + PARAM_CUSTOMER_NUMBER)
public class Customer implements Serializable {

    public static final String GET_ALL = "Customer.getAllCustomers";
    public static final String FIND_BY_CUSTOMER_NUMBER = "Customer.findByCustomerNumber";
    public static final String CHECK_EXIST_BY_CUSTOMER_NUMBER = "Customer.checkExistByCustomerNumber";
    public static final String DELETE_BY_CUSTOMER_NUMBER = "Customer.deleteByCustomerNumber";
    public static final String PARAM_CUSTOMER_NUMBER = "CustomerNumber";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CUSTOMER_NUMBER", nullable = false, unique = true)
    private String customerNumber;
    @Column(name = "ADDRESS_NUMBER")
    private String addressNumber;
    @Column(name = "CUSTOMER_NAME")
    private String name;
    @Transient
    private Address address;

    protected Customer() {
        // for framework
    }

    public Customer(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getId() {
        return id;
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

    void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    void addAddress(Address address) {
        this.address = address;
        setAddressNumber(address.getAddressNumber());
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
                + "id=" + id
                + ", customerNumber='" + customerNumber + "'"
                + ", addressNumber='" + addressNumber + "'"
                + ", name='" + name + "'"
                + "}";
    }
}
