package de.tdbit.presentations.controller;

import de.tdbit.presentations.dto.CustomerDto;
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

@Tag(name = "Kunden", description = "Kümmert sich um Kunden")
public interface CustomerControllerApi {

    @APIResponses(value = {
            @APIResponse(responseCode = "200",
                    description = "Liefert alle Kunden",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.ARRAY, implementation = CustomerDto.class)))
    })
    @Operation(summary = "Kunden liefern",
            description = "Liefert alle Kunden",
            operationId = "getCustomers")
    List<CustomerDto> getCustomers();

    @APIResponses(value = {
            @APIResponse(responseCode = "200",
                    description = "Liefert einen Kunden anhand der Kundennummer",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT, implementation = CustomerDto.class))),
            @APIResponse(responseCode = "404",
                    description = "Kunde wurde nicht gefunden.")
    })
    @Operation(summary = "Einen Kunden liefern",
            description = "Liefert einen Kunden anhand der Kundennummer",
            operationId = "getCustomer")
    CustomerDto getCustomer(String customerNumber);

    @APIResponses(value = {
            @APIResponse(responseCode = "200",
                    description = "Legt einen Kunden an",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT, implementation = CustomerDto.class)))
    })
    @Operation(summary = "Kunden anlegen",
            description = "Legt einen Kunden an",
            operationId = "addCustomer")
    CustomerDto addCustomer(@RequestBody(description = "Der neue Kunde", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = CustomerDto.class)))
                            CustomerDto customer);

    @APIResponses(value = {
            @APIResponse(responseCode = "204",
                    description = "Löscht einen Adressen")
    })
    @Operation(summary = "Kunde löschen",
            description = "Löscht den Kunden anhand der Kundennummer",
            operationId = "deleteCustomer")
    Response deleteCustomer(String customerNumber);

}
