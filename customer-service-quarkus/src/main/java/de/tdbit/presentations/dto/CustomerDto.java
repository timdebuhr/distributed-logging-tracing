package de.tdbit.presentations.dto;

import de.tdbit.presentations.domain.customer.Customer;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Customer", description = "Ein Kunde")
public record CustomerDto(
        @Schema(description = "Die Kundennummer", type = SchemaType.STRING, required = true, example = "CUS_1")
        @JsonbProperty("customerNumber")
        String customerNumber,
        @Schema(description = "Die Kundenname", type = SchemaType.STRING, required = true, example = "CUS_1")
        @JsonbProperty("customerName")
        String customerName,
        @Schema(description = "Die Adresse des Kunden", type = SchemaType.OBJECT, implementation = AddressDto.class)
        @JsonbProperty("address")
        AddressDto address
) {

    @JsonbCreator
    public CustomerDto(String customerNumber,
                       String customerName,
                       AddressDto address) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.address = address;
    }

    public CustomerDto(Customer customer) {
        this(customer.getCustomerNumber(),
                customer.getName(),
                customer.getAddress() != null ? new AddressDto(customer.getAddress()) : null);
    }

    public Customer asEntity() {
        Customer customer = new Customer(customerNumber);
        customer.setName(customerName);
        return customer;
    }

}
