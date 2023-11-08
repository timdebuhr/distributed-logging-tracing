package de.tdbit.presentations.controller;

import de.tdbit.presentations.domain.customer.CustomerNotFoundException;
import de.tdbit.presentations.dto.ErrorDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class CustomerNotFoundMapper implements ExceptionMapper<CustomerNotFoundException> {

    public static final Logger LOG = LoggerFactory.getLogger(CustomerNotFoundMapper.class);

    public CustomerNotFoundMapper() {
        LOG.info("Creating exception mapper for type: CustomerNotFound");
    }

    @Override
    public Response toResponse(CustomerNotFoundException e) {
        LOG.debug("CustomerNotFoundMapper: Details zum erwartet gefangenen Fehler: " + e.getMessage(), e);
        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorDto(e.getCustomerNumber(), e.getMessage()))
                .build();
    }
}
