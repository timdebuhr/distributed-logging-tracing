package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.OrderAlreadyExistsException;
import de.tdbit.presentations.dto.ErrorDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class OrderAlreadyExistsMapper implements ExceptionMapper<OrderAlreadyExistsException> {

    public static final Logger LOG = LoggerFactory.getLogger(OrderAlreadyExistsMapper.class);

    public OrderAlreadyExistsMapper() {
        LOG.info("Creating exception mapper for type: OrderAlreadyExists");
    }

    @Override
    public Response toResponse(OrderAlreadyExistsException e) {
        LOG.debug("OrderAlreadyExistsMapper: Details zum erwartet gefangenen Fehler: " + e.getMessage(), e);
        return Response.status(Response.Status.CONFLICT)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorDto(e.getCustomerNumber(), e.getMessage()))
                .build();
    }
}
