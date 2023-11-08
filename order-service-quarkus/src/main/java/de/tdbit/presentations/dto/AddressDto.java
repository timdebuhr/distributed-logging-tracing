package de.tdbit.presentations.dto;

import de.tdbit.presentations.domain.customer.address.Address;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Adresse", description = "Ein Adressen")
public record AddressDto(
        @Schema(description = "Die Adressnummer", type = SchemaType.STRING, required = true, example = "ADD_1")
        @JsonbProperty("addressNumber")
        String addressNumber,
        @Schema(description = "Die erste Adresszeile", type = SchemaType.STRING, example = "Kundenvorname Kundennachname")
        @JsonbProperty("addressLineOne")
        String addressLineOne,
        @Schema(description = "Die zweite Adresszeile", type = SchemaType.STRING, example = "Musterstrasse 21")
        @JsonbProperty("addressLineTwo")
        String addressLineTwo,
        @Schema(description = "Die dritte Adresszeile", type = SchemaType.STRING, example = "12345 Musterhausen")
        @JsonbProperty("addressLineThree")
        String addressLineThree,
        @Schema(description = "Die vierte Adresszeile", type = SchemaType.STRING)
        @JsonbProperty("addressLineFour")
        String addressLineFour) implements Serializable {

    @JsonbCreator
    public AddressDto(String addressNumber,
                      String addressLineOne,
                      String addressLineTwo,
                      String addressLineThree,
                      String addressLineFour) {
        this.addressNumber = addressNumber;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.addressLineThree = addressLineThree;
        this.addressLineFour = addressLineFour;
    }

    public AddressDto(Address address) {
        this(address.getAddressNumber(),
                address.getAddressLineOne(),
                address.getAddressLineTwo(),
                address.getAddressLineThree(),
                address.getAddressLineFour());
    }

    public Address asEntity() {
        Address address = new Address(addressNumber);
        address.setAddressLineOne(addressLineOne);
        address.setAddressLineTwo(addressLineTwo);
        address.setAddressLineThree(addressLineThree);
        address.setAddressLineFour(addressLineFour);
        return address;
    }

}
