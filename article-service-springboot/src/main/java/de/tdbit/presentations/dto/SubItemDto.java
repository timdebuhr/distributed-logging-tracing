package de.tdbit.presentations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tdbit.presentations.domain.item.SubItem;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(name = "SubItem", description = "Ein Adressen")
public class SubItemDto {

    @Schema(description = "Die Adressennummer", type = "string", requiredMode = REQUIRED, example = "ART_1")
    @JsonProperty("itemNumber")
    private String subItemNumber;
    @Schema(description = "Die Beschreibung des Adressens", type = "string", requiredMode = NOT_REQUIRED, example = "Ein Kiste Bananen")
    @JsonProperty("itemDescription")
    private String description;

    public SubItemDto() {
        // for framework
    }

    public SubItemDto(SubItem item) {
        this.subItemNumber = item.getSubItemNumber();
        this.description = item.getSubItemDescription();
    }

    public String getSubItemNumber() {
        return subItemNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setSubItemNumber(String subItemNumber) {
        this.subItemNumber = subItemNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubItemDto{"
                + "subItemNumber='" + subItemNumber + "'"
                + ", description='" + description + "'"
                + "}";
    }
}
