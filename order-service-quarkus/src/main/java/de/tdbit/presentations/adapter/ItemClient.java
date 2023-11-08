package de.tdbit.presentations.adapter;

import de.tdbit.presentations.dto.ItemDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/workshop/api")
@Produces(MediaType.APPLICATION_JSON)
public interface ItemClient {

    @GET
    @Path("/items/{itemNumber}")
    ItemDto getItem(@PathParam("itemNumber") String itemNumber);

    @POST
    @Path("/items")
    @Consumes(MediaType.APPLICATION_JSON)
    ItemDto addItem(ItemDto item);

    @DELETE
    @Path("/items/{itemNumber}")
    Response deleteItem(@PathParam("itemNumber") String itemNumber);
}
