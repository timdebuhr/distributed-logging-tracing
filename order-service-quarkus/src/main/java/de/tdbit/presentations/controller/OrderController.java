package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.Order;
import de.tdbit.presentations.domain.OrderService;
import de.tdbit.presentations.dto.OrderDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OrderController implements OrderControllerApi {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Inject
    OrderService orderService;

    public OrderController() {
        LOG.info("Creating controller: Order");
    }

    @Override
    @GET
    public List<OrderDto> getOrders() {
        LOG.info("Request orders");
        List<Order> orders = orderService.getAll();
        LOG.info("Loaded orders: {}", orders);
        return orders.stream().map(OrderDto::new).toList();
    }

    @Override
    @Path("/{orderNumber}")
    @GET
    public OrderDto getOrder(@PathParam("orderNumber") String orderNumber) {
        LOG.info("Request order by number: {}", orderNumber);
        Order order = orderService.get(orderNumber);
        return new OrderDto(order);
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDto addOrder(OrderDto order) {
        LOG.info("Add order: {}", order);
        Order newOrder = orderService.create(order.asEntity());
        return new OrderDto(newOrder);
    }

    @Override
    @Path("/{orderNumber}")
    @DELETE
    public Response deleteOrder(@PathParam("orderNumber") String orderNumber) {
        LOG.info("Delete order by number: {}", orderNumber);
        orderService.remove(orderNumber);
        return Response.noContent().build();
    }
}
