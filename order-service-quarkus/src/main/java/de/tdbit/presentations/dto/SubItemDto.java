package de.tdbit.presentations.dto;

import de.tdbit.presentations.domain.item.SubItem;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "SubItem", description = "Ein Adressen")
public record SubItemDto(
        @Schema(description = "Die Adressennummer", type = SchemaType.STRING, required = true, example = "ART_1")
        @JsonbProperty("itemNumber")
        String itemNumber,
        @Schema(description = "Die Beschreibung des Adressens", type = SchemaType.STRING, example = "Ein Kiste Bananen")
        @JsonbProperty("itemDescription")
        String itemDescription
) implements Serializable {

    @JsonbCreator
    public SubItemDto(String itemNumber, String itemDescription) {
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
    }

    public SubItemDto(SubItem subItem) {
        this(subItem.getSubItemNumber(), subItem.getSubItemDescription());
    }

    public SubItem asEntity() {
        SubItem subItem = new SubItem(itemNumber);
        subItem.setDescription(itemDescription);
        return subItem;
    }

}
