package de.tdbit.presentations.controller;

import de.tdbit.presentations.dto.AddressDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Adressen", description = "Kümmert sich um Adressen")
public interface AddressControllerApi {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liefert alle Adressen",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(type = "array", implementation = AddressDto.class)))
    })
    @Operation(summary = "Adressen liefern",
            description = "Liefert alle Adressen",
            operationId = "getAddresses")
    List<AddressDto> getAddresses();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liefert einen Adressen anhand der Adressennummer",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(type = "object", implementation = AddressDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Adressen wurde nicht gefunden.")
    })
    @Operation(summary = "Eine Adresse liefern",
            description = "Liefert eine Adresse anhand der Adressnummer",
            operationId = "getAddress")
    AddressDto getAddress(String addressNumber);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Legt eine Adresse an",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(type = "object", implementation = AddressDto.class)))
    })
    @Operation(summary = "Adresse anlegen",
            description = "Legt eine Adresse an",
            operationId = "addAddress")
    AddressDto addAddress(@RequestBody(description = "Der neue Adresse",
            required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(type = "object", implementation = AddressDto.class))) AddressDto address);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Löscht eine Adresse")
    })
    @Operation(summary = "Adresse löschen",
            description = "Löscht die Adresse anhand der Adressnummer",
            operationId = "deleteAddress")
    ResponseEntity<Object> deleteAddress(String addressNumber);

}
