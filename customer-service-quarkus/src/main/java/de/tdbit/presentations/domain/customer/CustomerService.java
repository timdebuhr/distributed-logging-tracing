package de.tdbit.presentations.domain.customer;

import de.tdbit.presentations.adapter.AddressClient;
import de.tdbit.presentations.domain.address.Address;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Inject
    CustomerRepository repository;

    @Inject
    @RestClient
    AddressClient client;

    public List<Customer> getAll() {
        List<Customer> customers = repository.getCustomers();
        customers.forEach(this::enrichAddress);
        return customers;
    }

    public Customer get(String customerNumber) {
        Optional<Customer> customerOptional = repository.getCustomer(customerNumber);
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(customerNumber);
        }
        Customer customer = customerOptional.get();
        enrichAddress(customer);
        return customer;
    }

    @Transactional
    public Customer create(Customer customer) {
        if (repository.exists(customer.getCustomerNumber())) {
            throw new CustomerAlreadyExistsException(customer.getCustomerNumber());
        }
        Customer storedCustomer = repository.store(customer);
        enrichAddress(storedCustomer);
        return storedCustomer;
    }

    @Transactional
    public void remove(String customerNumber) {
        repository.delete(customerNumber);
    }

    private void enrichAddress(Customer customer) {
        try {
            if (customer.getAddressNumber() != null) {
                Address address = client.getAddress(customer.getAddressNumber()).asEntity();
                customer.addAddress(address);
            }
        } catch (WebApplicationException e) {
            if (e.getResponse().getStatus() != 404) {
                LOG.error("Can't fetch address for number: " + customer.getAddressNumber(), e);
            }
        } catch (ProcessingException e) {
            LOG.error("Can't fetch address for number: " + customer.getAddressNumber(), e);
        }
    }
}
