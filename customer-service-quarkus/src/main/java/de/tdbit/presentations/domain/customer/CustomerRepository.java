package de.tdbit.presentations.domain.customer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> getCustomers() {
        return entityManager.createNamedQuery(Customer.GET_ALL, Customer.class)
                .getResultList();
    }

    public Optional<Customer> getCustomer(String customerNumber) {
        return entityManager.createNamedQuery(Customer.FIND_BY_CUSTOMER_NUMBER, Customer.class)
                .setParameter(Customer.PARAM_CUSTOMER_NUMBER, customerNumber)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Customer store(Customer customer) {
        return entityManager.merge(customer);
    }

    public void delete(String customerNumber) {
        entityManager.createNamedQuery(Customer.DELETE_BY_CUSTOMER_NUMBER)
                .setParameter(Customer.PARAM_CUSTOMER_NUMBER, customerNumber)
                .executeUpdate();
    }

    public boolean exists(String customerNumber) {
        return !entityManager.createNamedQuery(Customer.CHECK_EXIST_BY_CUSTOMER_NUMBER, String.class)
                .setParameter(Customer.PARAM_CUSTOMER_NUMBER, customerNumber)
                .getResultList()
                .isEmpty();
    }

}
