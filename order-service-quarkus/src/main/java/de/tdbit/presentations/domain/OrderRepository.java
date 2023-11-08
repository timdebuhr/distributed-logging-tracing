package de.tdbit.presentations.domain;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class OrderRepository {

    private final Map<String, Order> storage = new ConcurrentHashMap<>();

    public List<Order> getOrders() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Order> getOrder(String orderNumber) {
        return Optional.ofNullable(storage.get(orderNumber));
    }

    public Order store(Order order) {
        order.prepareToStore();
        storage.put(order.getOrderNumber(), order);
        return order;
    }

    public void delete(String orderNumber) {
        storage.remove(orderNumber);
    }

    public boolean exists(String orderNumber) {
        return storage.containsKey(orderNumber);
    }

    @PostConstruct
    public void init() {
        Order orderOne = new Order("ORD_1");
        orderOne.setCustomerNumber("CUS_1");
        orderOne.addItemNumber("ART_1");
        orderOne.addItemNumber("ART_2");
        store(orderOne);
        Order orderTwo = new Order("ORD_2");
        orderTwo.setCustomerNumber("CUS_2");
        store(orderTwo);
    }
}
