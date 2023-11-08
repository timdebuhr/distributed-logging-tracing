package de.tdbit.presentations.controller;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

    public static final Logger LOG = LoggerFactory.getLogger(ThrowableMapper.class);

    public ThrowableMapper() {
        LOG.info("Creating exception mapper for type: Throwable");
    }

    @Override
    public Response toResponse(Throwable e) {
        LOG.error("ThrowableMapper: Details zum unerwartet gefangenen Fehler: " + e.getMessage(), e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"message\": \""+ e.getMessage() + "\"}")
                .build();
    }
}
