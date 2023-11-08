package de.tdbit.presentations.adapter;

import de.tdbit.presentations.dto.CustomerDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/workshop/api")
@Produces(MediaType.APPLICATION_JSON)
public interface CustomerClient {

    @GET
    @Path("/customers/{customerNumber}")
    CustomerDto getCustomer(@PathParam("customerNumber") String customerNumber);

    @POST
    @Path("/customers")
    @Consumes(MediaType.APPLICATION_JSON)
    CustomerDto addCustomer(CustomerDto customer);

    @DELETE
    @Path("/customers/{customerNumber}")
    Response deleteCustomer(@PathParam("customerNumber") String customerNumber);
}
