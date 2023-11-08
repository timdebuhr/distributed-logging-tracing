package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.customer.Customer;
import de.tdbit.presentations.domain.customer.CustomerService;
import de.tdbit.presentations.dto.CustomerDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CustomerController implements CustomerControllerApi {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Inject
    CustomerService customerService;

    public CustomerController() {
        LOG.info("Creating controller: Customer");
    }

    @Override
    @GET
    public List<CustomerDto> getCustomers() {
        LOG.info("Request customers");
        List<Customer> customers = customerService.getAll();
        LOG.info("Loaded customers: {}", customers);
        return customers.stream().map(CustomerDto::new).toList();
    }

    @Override
    @Path("/{customerNumber}")
    @GET
    public CustomerDto getCustomer(@PathParam("customerNumber") String customerNumber) {
        LOG.info("Request customer by number: {}", customerNumber);
        Customer customer = customerService.get(customerNumber);
        return new CustomerDto(customer);
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public CustomerDto addCustomer(CustomerDto customer) {
        LOG.info("Add customer: {}", customer);
        Customer newCustomer = customerService.create(customer.asEntity());
        return new CustomerDto(newCustomer);
    }

    @Override
    @Path("/{customerNumber}")
    @DELETE
    public Response deleteCustomer(@PathParam("customerNumber") String customerNumber) {
        LOG.info("Delete customer by number: {}", customerNumber);
        customerService.remove(customerNumber);
        return Response.noContent().build();
    }
}
