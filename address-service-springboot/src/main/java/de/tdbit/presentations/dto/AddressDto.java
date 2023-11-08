package de.tdbit.presentations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.tdbit.presentations.domain.address.Address;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(name = "Adresse", description = "Ein Adressen")
public class AddressDto {

    @Schema(description = "Die Adressnummer", type = "string", requiredMode = REQUIRED, example = "ADD_1")
    @JsonProperty("addressNumber")
    private String addressNumber;
    @Schema(description = "Die erste Adresszeile", type = "string", requiredMode = NOT_REQUIRED, example = "Kundenvorname Kundennachname")
    @JsonProperty("addressLineOne")
    private String addressLineOne;
    @Schema(description = "Die zweite Adresszeile", type = "string", requiredMode = NOT_REQUIRED, example = "Musterstrasse 21")
    @JsonProperty("addressLineTwo")
    private String addressLineTwo;
    @Schema(description = "Die dritte Adresszeile", type = "string", requiredMode = NOT_REQUIRED, example = "12345 Musterhausen")
    @JsonProperty("addressLineThree")
    private String addressLineThree;
    @Schema(description = "Die vierte Adresszeile", type = "string", requiredMode = NOT_REQUIRED)
    @JsonProperty("addressLineFour")
    private String addressLineFour;

    public AddressDto() {
        // for framework
    }

    public AddressDto(Address address) {
        this.addressNumber = address.getAddressNumber();
        this.addressLineOne = address.getAddressLineOne();
        this.addressLineTwo = address.getAddressLineTwo();
        this.addressLineThree = address.getAddressLineThree();
        this.addressLineFour = address.getAddressLineFour();
    }

    public Address asEntity() {
        Address address = new Address(addressNumber);
        address.setAddressLineOne(addressLineOne);
        address.setAddressLineTwo(addressLineTwo);
        address.setAddressLineThree(addressLineThree);
        address.setAddressLineFour(addressLineFour);
        return address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public String getAddressLineFour() {
        return addressLineFour;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public void setAddressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
    }

    public void setAddressLineFour(String addressLineFour) {
        this.addressLineFour = addressLineFour;
    }

    @Override
    public String toString() {
        return "AddressDto{"
                + "addressNumber='" + addressNumber + "'"
                + ", addressLineOne='" + addressLineOne + "'"
                + ", addressLineTwo='" + addressLineTwo + "'"
                + ", addressLineThree='" + addressLineThree + "'"
                + ", addressLineFour='" + addressLineFour + "'"
                + "}";
    }
}
