package de.tdbit.presentations.domain;

import de.tdbit.presentations.adapter.CustomerClient;
import de.tdbit.presentations.adapter.ItemClient;
import de.tdbit.presentations.domain.customer.Customer;
import de.tdbit.presentations.domain.item.Item;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Inject
    OrderRepository repository;

    @Inject
    @RestClient
    CustomerClient customerClient;

    @Inject
    @RestClient
    ItemClient itemClient;

    public List<Order> getAll() {
        List<Order> orders = repository.getOrders();
        orders.forEach(order -> {
            enrichCustomer(order);
            enrichItems(order);
        });
        return orders;
    }

    public Order get(String orderNumber) {
        Optional<Order> orderOptional = repository.getOrder(orderNumber);
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException(orderNumber);
        }
        Order order = orderOptional.get();
        enrichCustomer(order);
        enrichItems(order);
        return order;
    }

    @Transactional
    public Order create(Order order) {
        if (repository.exists(order.getOrderNumber())) {
            throw new OrderAlreadyExistsException(order.getOrderNumber());
        }

        Order storedOrder = repository.store(order);
        enrichCustomer(storedOrder);
        enrichItems(storedOrder);
        return storedOrder;
    }

    @Transactional
    public void remove(String orderNumber) {
        repository.delete(orderNumber);
    }

    private void enrichCustomer(Order order) {
        try {
            if (order.getCustomerNumber() != null) {
                Customer customer = customerClient.getCustomer(order.getCustomerNumber()).asEntity();
                LOG.info("loaded customer: {}", customer);
                order.add(customer);
            }
        } catch (WebApplicationException e) {
            if (e.getResponse().getStatus() != 404) {
                LOG.error("Can't fetch customer for number: " + order.getCustomerNumber(), e);
            }
        }
    }

    private void enrichItems(Order order) {
        for (String itemNumber : new ArrayList<>(order.getItemNumbers())) {
            try {
                Item item = itemClient.getItem(itemNumber).asEntity();
                LOG.info("loaded item: {}", item);
                order.add(item);
            } catch (WebApplicationException e) {
                if (e.getResponse().getStatus() != 404) {
                    LOG.error("Can't fetch item for number: " + itemNumber, e);
                }
            }
        }
    }
}
