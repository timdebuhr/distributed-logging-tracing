package de.tdbit.presentations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tdbit.presentations.domain.item.Item;
import de.tdbit.presentations.domain.item.SubItem;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(name = "Item", description = "Ein Adressen")
public class ItemDto {

    @Schema(description = "Die Adressennummer", type = "string", requiredMode = REQUIRED, example = "ART_1")
    @JsonProperty("itemNumber")
    private String itemNumber;
    @Schema(description = "Die Beschreibung des Adressens", type = "string", requiredMode = NOT_REQUIRED, example = "Ein Kiste Bananen")
    @JsonProperty("itemDescription")
    private String description;

    @JsonProperty("subItems")
    private List<SubItemDto> subItems;

    public ItemDto() {
        // for framework
    }

    public ItemDto(Item item) {
        this.itemNumber = item.getItemNumber();
        this.description = item.getDescription();
        this.subItems = item.getSubItems().stream().map(SubItemDto::new).toList();
    }

    public Item asEntity() {
        Item item = new Item(itemNumber);
        item.setDescription(description);
        subItems.forEach(dto -> {
            SubItem subItem = new SubItem(dto.getSubItemNumber());
            subItem.setSubItemDescription(dto.getDescription());
            item.add(subItem);
        });
        return item;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getDescription() {
        return description;
    }

    public List<SubItemDto> getSubItems() {
        return subItems;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubItems(List<SubItemDto> subItems) {
        this.subItems = subItems;
    }

    @Override
    public String toString() {
        return "ItemDto{"
                + "itemNumber='" + itemNumber + "'"
                + ", description='" + description + "'"
                + ", subItems=" + subItems
                + "}";
    }
}
