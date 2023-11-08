package de.tdbit.presentations.controller;

import de.tdbit.presentations.dto.OrderDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Bestellung", description = "Kümmert sich um Bestellungen")
public interface OrderControllerApi {

    @APIResponses(value = {
            @APIResponse(responseCode = "200",
                    description = "Liefert alle Bestellungen",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.ARRAY, implementation = OrderDto.class)))
    })
    @Operation(summary = "Bestellungen liefern",
            description = "Liefert alle Bestellungen",
            operationId = "getOrders")
    List<OrderDto> getOrders();

    @APIResponses(value = {
            @APIResponse(responseCode = "200",
                    description = "Liefert eine Bestellung anhand der Bestellnummer",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT, implementation = OrderDto.class))),
            @APIResponse(responseCode = "404",
                    description = "Bestellung wurde nicht gefunden.")
    })
    @Operation(summary = "Eine Bestellung liefern",
            description = "Liefert eine Bestellung anhand der Bestellnummer",
            operationId = "getOrder")
    OrderDto getOrder(String orderNumber);

    @APIResponses(value = {
            @APIResponse(responseCode = "200",
                    description = "Legt eine Bestellung an",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT, implementation = OrderDto.class)))
    })
    @Operation(summary = "Bestellung anlegen",
            description = "Legt eine Bestellung an",
            operationId = "addOrder")
    OrderDto addOrder(@RequestBody(description = "Der neue Bestellung", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = OrderDto.class)))
                         OrderDto order);

    @APIResponses(value = {
            @APIResponse(responseCode = "204",
                    description = "Löscht einen Adressen")
    })
    @Operation(summary = "Bestellung löschen",
            description = "Löscht die Bestellung anhand der Bestellnummer",
            operationId = "deleteOrder")
    Response deleteOrder(String orderNumber);

}
