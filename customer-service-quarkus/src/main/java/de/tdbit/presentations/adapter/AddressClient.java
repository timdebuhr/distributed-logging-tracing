package de.tdbit.presentations.adapter;

import de.tdbit.presentations.dto.AddressDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/workshop/api")
@Produces(MediaType.APPLICATION_JSON)
public interface AddressClient {

    @GET
    @Path("/addresses/{addressNumber}")
    AddressDto getAddress(@PathParam("addressNumber") String addressNumber);

    @POST
    @Path("/addresses")
    @Consumes(MediaType.APPLICATION_JSON)
    AddressDto addAddress(AddressDto address);

    @DELETE
    @Path("/addresses/{addressNumber}")
    Response deleteAddress(@PathParam("addressNumber") String addressNumber);

}
