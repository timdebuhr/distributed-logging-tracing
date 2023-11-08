package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.OrderNotFoundException;
import de.tdbit.presentations.dto.ErrorDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class CustomerNotFoundMapper implements ExceptionMapper<OrderNotFoundException> {

    public static final Logger LOG = LoggerFactory.getLogger(CustomerNotFoundMapper.class);

    public CustomerNotFoundMapper() {
        LOG.info("Creating exception mapper for type: OrderNotFound");
    }

    @Override
    public Response toResponse(OrderNotFoundException e) {
        LOG.debug("OrderNotFoundMapper: Details zum erwartet gefangenen Fehler: " + e.getMessage(), e);
        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorDto(e.getCustomerNumber(), e.getMessage()))
                .build();
    }
}
