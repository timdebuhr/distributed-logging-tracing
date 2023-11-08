package de.tdbit.presentations.controller;

import de.tdbit.presentations.dto.ItemDto;
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

@Tag(name = "Artikel", description = "Kümmert sich um Artikel")
public interface ItemControllerApi {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liefert alle Artikel",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(type = "array", implementation = ItemDto.class)))
    })
    @Operation(summary = "Artikel liefern",
            description = "Liefert alle Artikel",
            operationId = "getItems")
    List<ItemDto> getItems();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Liefert einen Artikel anhand der Artikelnummer",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(type = "object", implementation = ItemDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Artikel wurde nicht gefunden.")
    })
    @Operation(summary = "Einen Artikel liefern",
            description = "Liefert einen Artikel anhand der Artikelnummer",
            operationId = "getItem")
    ItemDto getItem(String itemNumber);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Legt einen Artikel an",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(type = "object", implementation = ItemDto.class)))
    })
    @Operation(summary = "Artikel anlegen",
            description = "Legt einen Artikel an",
            operationId = "addItem")
    ItemDto addItem(@RequestBody(description = "Der neue Artikel",
            required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(type = "object", implementation = ItemDto.class))) ItemDto item);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Löscht einen Artikel")
    })
    @Operation(summary = "Artikel löschen",
            description = "Löscht den Artikel anhand der Artikelnummer",
            operationId = "deleteItem")
    ResponseEntity<Object> deleteItem(String itemNumber);

}
