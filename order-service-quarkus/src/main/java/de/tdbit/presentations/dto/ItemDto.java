package de.tdbit.presentations.dto;

import de.tdbit.presentations.domain.item.Item;
import de.tdbit.presentations.domain.item.SubItem;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Item", description = "Ein Artikel")
public record ItemDto(
        @Schema(description = "Die Artikelnummer", type = SchemaType.STRING, required = true, example = "ART_1")
        @JsonbProperty("itemNumber")
        String itemNumber,
        @Schema(description = "Die Beschreibung des Artikels", type = SchemaType.STRING, example = "Ein Kiste Bananen")
        @JsonbProperty("itemDescription")
        String description,
        @Schema(description = "Die Unterartikel des Artikels", type = SchemaType.ARRAY)
        @JsonbProperty("subItems")
        List<SubItemDto> subItems
) implements Serializable {

    @JsonbCreator
    public ItemDto(String itemNumber,
                   String description,
                   List<SubItemDto> subItems) {
        this.itemNumber = itemNumber;
        this.description = description;
        this.subItems = subItems;
    }

    public ItemDto(Item item) {
        this(item.getItemNumber(),
                item.getDescription(),
                item.getSubItems().stream().map(SubItemDto::new).toList());
    }

    public Item asEntity() {
        Item item = new Item(itemNumber);
        item.setDescription(description);
        if (subItems != null) {
            subItems.forEach(subItemDto -> {
                SubItem subItem = subItemDto.asEntity();
                item.add(subItem);
            });
        }
        return item;
    }

}
